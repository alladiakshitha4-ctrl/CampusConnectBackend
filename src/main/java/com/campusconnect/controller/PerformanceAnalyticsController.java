package com.campusconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.dto.PerformanceAnalyticsDTO;
import com.campusconnect.service.PerformanceAnalyticsService;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "http://localhost:5173")
public class PerformanceAnalyticsController {

    @Autowired
    private PerformanceAnalyticsService analyticsService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<PerformanceAnalyticsDTO>
            getStudentPerformance(@PathVariable Long studentId) {

        return ResponseEntity.ok(
                analyticsService.getStudentPerformance(studentId)
        );
    }
}