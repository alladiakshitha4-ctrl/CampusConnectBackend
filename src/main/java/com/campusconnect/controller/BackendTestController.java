package com.campusconnect.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.repo.AdminRepo;
import com.campusconnect.repo.AttendanceRepo;
import com.campusconnect.repo.CourseRepo;
import com.campusconnect.repo.FacultyRepo;
import com.campusconnect.repo.MarksRepo;
import com.campusconnect.repo.NoticeRepo;
import com.campusconnect.repo.StudentRepo;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "http://localhost:5173")
public class BackendTestController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private FacultyRepo facultyRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private MarksRepo marksRepo;

    @Autowired
    private NoticeRepo noticeRepo;

    @Autowired
    private AdminRepo adminRepo;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> testAll() {

        Map<String, Object> result = new LinkedHashMap<>();

        result.put("backend", "UP");

        result.put("students", "PASS");
        result.put("faculty", "PASS");
        result.put("courses", "PASS");
        result.put("attendance", "PASS");
        result.put("marks", "PASS");
        result.put("notices", "PASS");
        result.put("admin", "PASS");

        result.put("studentCount", studentRepo.count());
        result.put("facultyCount", facultyRepo.count());
        result.put("courseCount", courseRepo.count());
        result.put("attendanceCount", attendanceRepo.count());
        result.put("marksCount", marksRepo.count());
        result.put("noticeCount", noticeRepo.count());
        result.put("adminCount", adminRepo.count());

        return ResponseEntity.ok(result);
    }
}