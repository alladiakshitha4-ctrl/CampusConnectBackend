package com.campusconnect.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.repo.CourseRepo;
import com.campusconnect.repo.FacultyRepo;
import com.campusconnect.repo.NoticeRepo;
import com.campusconnect.repo.StudentRepo;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private FacultyRepo facultyRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private NoticeRepo noticeRepo;

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Long>> getDashboardSummary() {

        Map<String, Long> summary = new HashMap<>();

        summary.put("totalStudents", studentRepo.count());
        summary.put("totalFaculty", facultyRepo.count());
        summary.put("totalCourses", courseRepo.count());
        summary.put("totalNotices", noticeRepo.count());

        return ResponseEntity.ok(summary);
    }
}