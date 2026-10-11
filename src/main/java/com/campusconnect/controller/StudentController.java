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

import com.campusconnect.dto.StudentRequestDTO;
import com.campusconnect.dto.StudentResponseDTO;
import com.campusconnect.service.StudentService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    @Autowired
    private StudentService service;

    // =========================
    // REGISTER STUDENT
    // =========================
    @PostMapping("/register")
    public StudentResponseDTO register(
            @Valid @RequestBody StudentRequestDTO studentDTO) {

        return service.addStudent(studentDTO);
    }

    // =========================
    // GET ALL STUDENTS
    // PAGINATION + SORTING
    // =========================
    @GetMapping("/students")
    public Page<StudentResponseDTO> getStudents(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return service.getStudents(
                page,
                size,
                sortBy,
                direction
        );
    }

    // =========================
    // SEARCH STUDENTS
    // SEARCH + PAGINATION + SORTING
    // =========================
    @GetMapping("/students/search")
    public Page<StudentResponseDTO> searchStudents(

            @RequestParam String name,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return service.searchStudents(
                name,
                page,
                size,
                sortBy,
                direction
        );
    }

    // =========================
    // GET STUDENT BY ID
    // =========================
    @GetMapping("/students/{id}")
    public StudentResponseDTO getStudentById(
            @PathVariable Long id) {

        return service.getStudentById(id);
    }

    // =========================
    // UPDATE STUDENT
    // =========================
    @PutMapping("/students/{id}")
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO studentDTO) {

        return service.updateStudent(id, studentDTO);
    }

    // =========================
    // DELETE STUDENT
    // =========================
    @DeleteMapping("/students/{id}")
    public String deleteStudent(
            @PathVariable Long id) {

        service.deleteStudent(id);

        return "Student deleted successfully";
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public StudentResponseDTO login(
            @RequestBody StudentRequestDTO studentDTO) {

        return service.login(
                studentDTO.getEmail(),
                studentDTO.getPassword()
        );
    }
}