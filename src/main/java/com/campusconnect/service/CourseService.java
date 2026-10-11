package com.campusconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusconnect.dto.CourseRequestDTO;
import com.campusconnect.dto.CourseResponseDTO;
import com.campusconnect.entity.Course;
import com.campusconnect.entity.Faculty;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repo.CourseRepo;
import com.campusconnect.repo.FacultyRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private FacultyRepo facultyRepo;

    // =========================
    // ADD COURSE
    // =========================
    public CourseResponseDTO addCourse(CourseRequestDTO dto) {

        if (courseRepo.existsByCourseCode(dto.getCourseCode())) {
            throw new DuplicateResourceException(
                    "Course with code "
                    + dto.getCourseCode()
                    + " already exists"
            );
        }

        Faculty faculty = facultyRepo.findById(dto.getFacultyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Faculty with ID "
                                + dto.getFacultyId()
                                + " not found"
                        )
                );

        Course course = new Course();

        course.setCourseCode(dto.getCourseCode());
        course.setCourseName(dto.getCourseName());
        course.setCredits(dto.getCredits());
        course.setDepartment(dto.getDepartment());
        course.setFaculty(faculty);

        Course savedCourse = courseRepo.save(course);

        return convertToResponseDTO(savedCourse);
    }

    // =========================
    // GET ALL COURSES
    // =========================
    public Page<CourseResponseDTO> getCourses(
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

        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );

        return courseRepo.findAll(pageable)
                .map(this::convertToResponseDTO);
    }

    // =========================
    // SEARCH COURSES
    // =========================
    public Page<CourseResponseDTO> searchCourses(
            String courseName,
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

        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );

        return courseRepo
                .findByCourseNameContainingIgnoreCase(
                        courseName,
                        pageable
                )
                .map(this::convertToResponseDTO);
    }

    // =========================
    // GET COURSE BY ID
    // =========================
    public CourseResponseDTO getCourseById(Long id) {

        Course course = courseRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID "
                                + id
                                + " not found"
                        )
                );

        return convertToResponseDTO(course);
    }

    // =========================
    // UPDATE COURSE
    // =========================
    public CourseResponseDTO updateCourse(
            Long id,
            CourseRequestDTO dto) {

        Course existingCourse = courseRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID "
                                + id
                                + " not found"
                        )
                );

        if (!existingCourse.getCourseCode().equals(dto.getCourseCode())
                && courseRepo.existsByCourseCode(dto.getCourseCode())) {

            throw new DuplicateResourceException(
                    "Course with code "
                    + dto.getCourseCode()
                    + " already exists"
            );
        }

        Faculty faculty = facultyRepo.findById(dto.getFacultyId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Faculty with ID "
                                + dto.getFacultyId()
                                + " not found"
                        )
                );

        existingCourse.setCourseCode(dto.getCourseCode());
        existingCourse.setCourseName(dto.getCourseName());
        existingCourse.setCredits(dto.getCredits());
        existingCourse.setDepartment(dto.getDepartment());
        existingCourse.setFaculty(faculty);

        Course updatedCourse = courseRepo.save(existingCourse);

        return convertToResponseDTO(updatedCourse);
    }

    // =========================
    // DELETE COURSE
    // =========================
    public void deleteCourse(Long id) {

        if (!courseRepo.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Course with ID "
                    + id
                    + " not found"
            );
        }

        courseRepo.deleteById(id);
    }

    // =========================
    // ENTITY → RESPONSE DTO
    // =========================
    private CourseResponseDTO convertToResponseDTO(
            Course course) {

        Faculty faculty = course.getFaculty();

        return new CourseResponseDTO(
                course.getId(),
                course.getCourseCode(),
                course.getCourseName(),
                course.getCredits(),
                course.getDepartment(),
                faculty.getId(),
                faculty.getName()
        );
    }
}