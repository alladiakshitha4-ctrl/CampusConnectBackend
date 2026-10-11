package com.campusconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campusconnect.dto.FacultyRequestDTO;
import com.campusconnect.dto.FacultyResponseDTO;
import com.campusconnect.entity.Faculty;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repo.FacultyRepo;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // =====================================================
    // REGISTER FACULTY
    // =====================================================

    public FacultyResponseDTO addFaculty(FacultyRequestDTO dto) {

        // Check duplicate email
        if (repo.existsByEmail(dto.getEmail())) {

            throw new DuplicateResourceException(
                    "Faculty with email "
                    + dto.getEmail()
                    + " already exists"
            );
        }

        // Check duplicate employee ID
        if (repo.existsByEmployeeId(dto.getEmployeeId())) {

            throw new DuplicateResourceException(
                    "Faculty with employee ID "
                    + dto.getEmployeeId()
                    + " already exists"
            );
        }

        Faculty faculty = new Faculty();

        faculty.setName(dto.getName());
        faculty.setEmail(dto.getEmail());

        // Encode password using BCrypt
        faculty.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        faculty.setPhone(dto.getPhone());
        faculty.setDepartment(dto.getDepartment());
        faculty.setDesignation(dto.getDesignation());
        faculty.setEmployeeId(dto.getEmployeeId());

        // Automatically assign FACULTY role
        faculty.setRole("FACULTY");

        Faculty savedFaculty = repo.save(faculty);

        return convertToResponseDTO(savedFaculty);
    }

    // =====================================================
    // GET ALL FACULTY
    // PAGINATION + SORTING
    // =====================================================

    public Page<FacultyResponseDTO> getFaculty(
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
    // SEARCH FACULTY
    // SEARCH + PAGINATION + SORTING
    // =====================================================

    public Page<FacultyResponseDTO> searchFaculty(
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

        return repo.findByNameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToResponseDTO);
    }

    // =====================================================
    // GET FACULTY BY ID
    // =====================================================

    public FacultyResponseDTO getFacultyById(Long id) {

        Faculty faculty = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Faculty with ID "
                                + id
                                + " not found"
                        )
                );

        return convertToResponseDTO(faculty);
    }

    // =====================================================
    // UPDATE FACULTY
    // =====================================================

    public FacultyResponseDTO updateFaculty(
            Long id,
            FacultyRequestDTO dto) {

        Faculty existingFaculty = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Faculty with ID "
                                + id
                                + " not found"
                        )
                );

        // Check duplicate email only if email changed
        if (!existingFaculty.getEmail().equals(dto.getEmail())
                && repo.existsByEmail(dto.getEmail())) {

            throw new DuplicateResourceException(
                    "Faculty with email "
                    + dto.getEmail()
                    + " already exists"
            );
        }

        // Check duplicate employee ID only if changed
        if (!existingFaculty.getEmployeeId()
                .equals(dto.getEmployeeId())
                && repo.existsByEmployeeId(
                        dto.getEmployeeId())) {

            throw new DuplicateResourceException(
                    "Faculty with employee ID "
                    + dto.getEmployeeId()
                    + " already exists"
            );
        }

        existingFaculty.setName(dto.getName());
        existingFaculty.setEmail(dto.getEmail());

        // Encode updated password using BCrypt
        existingFaculty.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        existingFaculty.setPhone(dto.getPhone());
        existingFaculty.setDepartment(dto.getDepartment());
        existingFaculty.setDesignation(dto.getDesignation());
        existingFaculty.setEmployeeId(dto.getEmployeeId());

        // Role is intentionally NOT taken from DTO.
        // Existing FACULTY role remains unchanged.

        Faculty updatedFaculty =
                repo.save(existingFaculty);

        return convertToResponseDTO(updatedFaculty);
    }

    // =====================================================
    // DELETE FACULTY
    // =====================================================

    public void deleteFaculty(Long id) {

        if (!repo.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Faculty with ID "
                    + id
                    + " not found"
            );
        }

        repo.deleteById(id);
    }

    // =====================================================
    // ENTITY → RESPONSE DTO
    // =====================================================

    private FacultyResponseDTO convertToResponseDTO(
            Faculty faculty) {

        return new FacultyResponseDTO(
                faculty.getId(),
                faculty.getName(),
                faculty.getEmail(),
                faculty.getPhone(),
                faculty.getDepartment(),
                faculty.getDesignation(),
                faculty.getEmployeeId()
        );
    }

	public FacultyResponseDTO login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}