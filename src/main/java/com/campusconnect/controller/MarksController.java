package com.campusconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.entity.Marks;
import com.campusconnect.service.MarksService;

@RestController
@RequestMapping("/marks")
@CrossOrigin(origins = "http://localhost:5173")
public class MarksController {

    @Autowired
    private MarksService service;

    // =========================
    // ADD MARKS
    // =========================
    @PostMapping
    public ResponseEntity<Marks> addMarks(
            @RequestBody Marks marks) {

        return ResponseEntity.ok(
                service.addMarks(marks)
        );
    }

    // =========================
    // GET ALL MARKS
    // =========================
    @GetMapping
    public ResponseEntity<Page<Marks>> getAllMarks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                service.getAllMarks(page, size)
        );
    }

    // =========================
    // GET MARKS BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Marks> getMarksById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getMarksById(id)
        );
    }

    // =========================
    // GET MARKS BY STUDENT
    // =========================
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Page<Marks>> getMarksByStudent(
            @PathVariable Long studentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                service.getMarksByStudent(
                        studentId,
                        page,
                        size
                )
        );
    }

    // =========================
    // GET MARKS BY COURSE
    // =========================
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Page<Marks>> getMarksByCourse(
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                service.getMarksByCourse(
                        courseId,
                        page,
                        size
                )
        );
    }

    // =========================
    // GET MARKS BY STUDENT AND COURSE
    // =========================
    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Page<Marks>> getMarksByStudentAndCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                service.getMarksByStudentAndCourse(
                        studentId,
                        courseId,
                        page,
                        size
                )
        );
    }

    // =========================
    // UPDATE MARKS
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<Marks> updateMarks(
            @PathVariable Long id,
            @RequestBody Marks marks) {

        return ResponseEntity.ok(
                service.updateMarks(
                        id,
                        marks
                )
        );
    }

    // =========================
    // DELETE MARKS
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMarks(
            @PathVariable Long id) {

        service.deleteMarks(id);

        return ResponseEntity.ok(
                "Marks deleted successfully"
        );
    }
}