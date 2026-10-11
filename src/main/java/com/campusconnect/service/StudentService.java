package com.campusconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campusconnect.dto.StudentRequestDTO;
import com.campusconnect.dto.StudentResponseDTO;
import com.campusconnect.entity.Student;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repo.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepo repo;

    // =====================================================
    // REGISTER STUDENT
    // =====================================================
    public StudentResponseDTO addStudent(StudentRequestDTO dto) {

        // Check duplicate email
        if (repo.existsByEmail(dto.getEmail())) {

            throw new DuplicateResourceException(
                    "Student with email "
                    + dto.getEmail()
                    + " already exists"
            );
        }

        Student student = new Student();

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        // Encode password using BCrypt
        student.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        student.setPhone(dto.getPhone());
        student.setDepartment(dto.getDepartment());

        // Automatically assign STUDENT role
        student.setRole("STUDENT");

        Student savedStudent = repo.save(student);

        return convertToResponseDTO(savedStudent);
    }

    // =====================================================
    // GET ALL STUDENTS
    // PAGINATION + SORTING
    // =====================================================
    public Page<StudentResponseDTO> getStudents(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {

            sort = Sort.by(sortBy).descending();

        } else {

            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return repo.findAll(pageable)
                .map(this::convertToResponseDTO);
    }

    // =====================================================
    // SEARCH STUDENTS
    // SEARCH + PAGINATION + SORTING
    // =====================================================
    public Page<StudentResponseDTO> searchStudents(
            String name,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {

            sort = Sort.by(sortBy).descending();

        } else {

            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return repo
                .findByNameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToResponseDTO);
    }

    // =====================================================
    // GET STUDENT BY ID
    // =====================================================
    public StudentResponseDTO getStudentById(Long id) {

        Student student = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with ID "
                                + id
                                + " not found"
                        )
                );

        return convertToResponseDTO(student);
    }

    // =====================================================
    // UPDATE STUDENT
    // =====================================================
    public StudentResponseDTO updateStudent(
            Long id,
            StudentRequestDTO dto) {

        Student existingStudent = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with ID "
                                + id
                                + " not found"
                        )
                );

        // Check duplicate email
        // Only if email is changed
        if (!existingStudent.getEmail().equals(dto.getEmail())
                && repo.existsByEmail(dto.getEmail())) {

            throw new DuplicateResourceException(
                    "Student with email "
                    + dto.getEmail()
                    + " already exists"
            );
        }

        existingStudent.setName(dto.getName());
        existingStudent.setEmail(dto.getEmail());

        // Encode updated password using BCrypt
        existingStudent.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        existingStudent.setPhone(dto.getPhone());
        existingStudent.setDepartment(dto.getDepartment());

        // Do NOT take role from the request DTO.
        // Keep the existing student's role.

        Student updatedStudent =
                repo.save(existingStudent);

        return convertToResponseDTO(updatedStudent);
    }

    // =====================================================
    // DELETE STUDENT
    // =====================================================
    public void deleteStudent(Long id) {

        if (!repo.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Student with ID "
                    + id
                    + " not found"
            );
        }

        repo.deleteById(id);
    }

    // =====================================================
    // LOGIN
    // =====================================================
    // This is the OLD login method.
    // We will replace it later with Spring Security + BCrypt.
    // Keep it for now so we don't break existing code.
    // =====================================================
    public StudentResponseDTO login(
            String email,
            String password) {

        Student student =
                repo.findByEmailAndPassword(
                        email,
                        password
                );

        if (student == null) {

            throw new ResourceNotFoundException(
                    "Invalid email or password"
            );
        }

        return convertToResponseDTO(student);
    }

    // =====================================================
    // ENTITY → RESPONSE DTO
    // =====================================================
    private StudentResponseDTO convertToResponseDTO(
            Student student) {

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getDepartment()
        );
    }
}