package com.campusconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campusconnect.entity.Admin;
import com.campusconnect.exception.DuplicateResourceException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repo.AdminRepo;

@Service
public class AdminService {

    @Autowired
    private AdminRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // =====================================================
    // CREATE ADMIN
    // =====================================================

    public Admin createAdmin(Admin admin) {

        // Check duplicate email
        if (repo.existsByEmail(admin.getEmail())) {

            throw new DuplicateResourceException(
                    "Admin with email "
                    + admin.getEmail()
                    + " already exists"
            );
        }

        // Encode password using BCrypt
        admin.setPassword(
                passwordEncoder.encode(admin.getPassword())
        );

        // Automatically assign ADMIN role
        admin.setRole("ADMIN");

        return repo.save(admin);
    }

    // =====================================================
    // GET ADMIN BY ID
    // =====================================================

    public Admin getAdminById(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Admin with ID "
                                + id
                                + " not found"
                        )
                );
    }

    // =====================================================
    // GET ADMIN BY EMAIL
    // =====================================================

    public Admin getAdminByEmail(String email) {

        return repo.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Admin with email "
                                + email
                                + " not found"
                        )
                );
    }
}