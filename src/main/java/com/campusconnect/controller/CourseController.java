package com.campusconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.dto.CourseRequestDTO;
import com.campusconnect.dto.CourseResponseDTO;
import com.campusconnect.service.CourseService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

    @Autowired
    private CourseService service;

    // =========================
    // ADD COURSE
    // =========================
    @PostMapping("/courses")
    public CourseResponseDTO addCourse(
            @Valid @RequestBody CourseRequestDTO courseDTO) {

        return service.addCourse(courseDTO);
    }

    // =========================
    // GET ALL COURSES
    // =========================
    @GetMapping("/courses")
    public Page<CourseResponseDTO> getCourses(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return service.getCourses(
                page,
                size,
                sortBy,
                direction
        );
    }

    // =========================
    // SEARCH COURSES
    // =========================
    @GetMapping("/courses/search")
    public Page<CourseResponseDTO> searchCourses(
            @RequestParam String courseName,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return service.searchCourses(
                courseName,
                page,
                size,
                sortBy,
                direction
        );
    }

    // =========================
    // GET COURSE BY ID
    // =========================
    @GetMapping("/courses/{id}")
    public CourseResponseDTO getCourseById(
            @PathVariable Long id) {

        return service.getCourseById(id);
    }

    // =========================
    // UPDATE COURSE
    // =========================
    @PutMapping("/courses/{id}")
    public CourseResponseDTO updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDTO courseDTO) {

        return service.updateCourse(
                id,
                courseDTO
        );
    }

    // =========================
    // DELETE COURSE
    // =========================
    @DeleteMapping("/courses/{id}")
    public String deleteCourse(
            @PathVariable Long id) {

        service.deleteCourse(id);

        return "Course deleted successfully";
    }
}