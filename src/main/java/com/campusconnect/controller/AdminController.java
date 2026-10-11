package com.campusconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.entity.Admin;
import com.campusconnect.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // =====================================================
    // CREATE ADMIN
    // =====================================================

    @PostMapping("/register")
    public ResponseEntity<Admin> createAdmin(
            @RequestBody Admin admin) {

        Admin savedAdmin =
                adminService.createAdmin(admin);

        return ResponseEntity.ok(savedAdmin);
    }
}