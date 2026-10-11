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

import com.campusconnect.dto.AttendanceRequestDTO;
import com.campusconnect.dto.AttendanceResponseDTO;
import com.campusconnect.dto.AttendanceSummaryDTO;
import com.campusconnect.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    // Add attendance
    @PostMapping("/attendance")
    public AttendanceResponseDTO addAttendance(
            @Valid @RequestBody AttendanceRequestDTO attendanceDTO) {

        return service.addAttendance(attendanceDTO);
    }

    // Get all attendance
    @GetMapping("/attendance")
    public Page<AttendanceResponseDTO> getAttendance(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return service.getAttendance(
                page,
                size,
                sortBy,
                direction
        );
    }

    // Get attendance by student
    @GetMapping("/attendance/student/{studentId}")
    public Page<AttendanceResponseDTO> getAttendanceByStudent(
            @PathVariable Long studentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getAttendanceByStudent(
                studentId,
                page,
                size
        );
    }

    // Get attendance by course
    @GetMapping("/attendance/course/{courseId}")
    public Page<AttendanceResponseDTO> getAttendanceByCourse(
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getAttendanceByCourse(
                courseId,
                page,
                size
        );
    }

    // Get attendance by student and course
    @GetMapping("/attendance/student/{studentId}/course/{courseId}")
    public Page<AttendanceResponseDTO> getStudentCourseAttendance(
            @PathVariable Long studentId,
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getStudentCourseAttendance(
                studentId,
                courseId,
                page,
                size
        );
    }

    // Get attendance by ID
    @GetMapping("/attendance/{id}")
    public AttendanceResponseDTO getAttendanceById(
            @PathVariable Long id) {

        return service.getAttendanceById(id);
    }

    // Update attendance
    @PutMapping("/attendance/{id}")
    public AttendanceResponseDTO updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceRequestDTO attendanceDTO) {

        return service.updateAttendance(
                id,
                attendanceDTO
        );
    }

    // Delete attendance
    @DeleteMapping("/attendance/{id}")
    public String deleteAttendance(
            @PathVariable Long id) {

        service.deleteAttendance(id);

        return "Attendance deleted successfully";
    }
 // Get attendance summary
    @GetMapping("/attendance/student/{studentId}/course/{courseId}/summary")
    public AttendanceSummaryDTO getAttendanceSummary(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        return service.getAttendanceSummary(
                studentId,
                courseId
        );
    }
}