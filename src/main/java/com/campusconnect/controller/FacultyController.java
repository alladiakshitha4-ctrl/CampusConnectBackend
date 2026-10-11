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

import com.campusconnect.dto.FacultyRequestDTO;
import com.campusconnect.dto.FacultyResponseDTO;
import com.campusconnect.service.FacultyService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class FacultyController {

    @Autowired
    private FacultyService service;

    // =========================
    // REGISTER FACULTY
    // =========================
    @PostMapping("/faculty/register")
    public FacultyResponseDTO registerFaculty(
            @Valid @RequestBody FacultyRequestDTO facultyDTO) {

        return service.addFaculty(facultyDTO);
    }

    // =========================
    // GET ALL FACULTY
    // =========================
    @GetMapping("/faculty")
    public Page<FacultyResponseDTO> getFaculty(
            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return service.getFaculty(
                page,
                size,
                sortBy,
                direction
        );
    }

    // =========================
    // SEARCH FACULTY
    // =========================
    @GetMapping("/faculty/search")
    public Page<FacultyResponseDTO> searchFaculty(
            @RequestParam String name,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return service.searchFaculty(
                name,
                page,
                size,
                sortBy,
                direction
        );
    }

    // =========================
    // GET FACULTY BY ID
    // =========================
    @GetMapping("/faculty/{id}")
    public FacultyResponseDTO getFacultyById(
            @PathVariable Long id) {

        return service.getFacultyById(id);
    }

    // =========================
    // UPDATE FACULTY
    // =========================
    @PutMapping("/faculty/{id}")
    public FacultyResponseDTO updateFaculty(
            @PathVariable Long id,
            @Valid @RequestBody FacultyRequestDTO facultyDTO) {

        return service.updateFaculty(
                id,
                facultyDTO
        );
    }

    // =========================
    // DELETE FACULTY
    // =========================
    @DeleteMapping("/faculty/{id}")
    public String deleteFaculty(
            @PathVariable Long id) {

        service.deleteFaculty(id);

        return "Faculty deleted successfully";
    }

    // =========================
    // FACULTY LOGIN
    // =========================
    @PostMapping("/faculty/login")
    public FacultyResponseDTO login(
            @RequestBody FacultyRequestDTO facultyDTO) {

        return service.login(
                facultyDTO.getEmail(),
                facultyDTO.getPassword()
        );
    }
}