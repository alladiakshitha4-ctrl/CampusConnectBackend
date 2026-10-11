
package com.campusconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.campusconnect.dto.LoginResponseDTO;
import com.campusconnect.entity.Student;
import com.campusconnect.entity.Faculty;
import com.campusconnect.entity.Admin;
import com.campusconnect.repo.StudentRepo;
import com.campusconnect.repo.FacultyRepo;
import com.campusconnect.repo.AdminRepo;

@Service
public class AuthService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private FacultyRepo facultyRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(String email, String password) {

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Email and password are required");
        }

        String cleanEmail = email.trim();

        Student student =
            studentRepo.findByEmail(cleanEmail).orElse(null);

        if (student != null && matches(password, student.getPassword())) {
            return new LoginResponseDTO(
                student.getId(), student.getName(), student.getEmail(),
                "STUDENT", "STUDENT");
        }

        Faculty faculty =
            facultyRepo.findByEmail(cleanEmail).orElse(null);

        if (faculty != null && matches(password, faculty.getPassword())) {
            return new LoginResponseDTO(
                faculty.getId(), faculty.getName(), faculty.getEmail(),
                "FACULTY", "FACULTY");
        }

        Admin admin =
            adminRepo.findByEmail(cleanEmail).orElse(null);

        if (admin != null && matches(password, admin.getPassword())) {
            return new LoginResponseDTO(
                admin.getId(), admin.getName(), admin.getEmail(),
                "ADMIN", "ADMIN");
        }

        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }

    // LOCAL DEMO ONLY: allows a user to set a password by email and role.
    // This does not verify account ownership.
    public void demoSetPassword(
            String email, String role, String newPassword) {

        if (email == null || email.isBlank()
                || role == null || role.isBlank()
                || newPassword == null
                || newPassword.length() < 8
                || newPassword.length() > 72) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Enter an email, role, and password of 8-72 characters");
        }

        String cleanEmail = email.trim();
        String cleanRole = role.trim().toUpperCase();

        String encodedPassword = passwordEncoder.encode(newPassword);

        switch (cleanRole) {
            case "STUDENT" -> {
                Student student =
                    studentRepo.findByEmail(cleanEmail).orElse(null);

                if (student == null) {
                    throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student account not found");
                }

                student.setPassword(encodedPassword);
                studentRepo.save(student);
            }

            case "FACULTY" -> {
                Faculty faculty =
                    facultyRepo.findByEmail(cleanEmail).orElse(null);

                if (faculty == null) {
                    throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Faculty account not found");
                }

                faculty.setPassword(encodedPassword);
                facultyRepo.save(faculty);
            }

            case "ADMIN" -> {
                Admin admin =
                    adminRepo.findByEmail(cleanEmail).orElse(null);

                if (admin == null) {
                    throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Admin account not found");
                }

                admin.setPassword(encodedPassword);
                adminRepo.save(admin);
            }

            default -> throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid account role");
        }
    }

    private boolean matches(String rawPassword, String storedPassword) {

        if (storedPassword == null || storedPassword.isBlank()) {
            return false;
        }

        if (!(storedPassword.startsWith("$2a$")
                || storedPassword.startsWith("$2b$")
                || storedPassword.startsWith("$2y$"))) {
            return false;
        }

        try {
            return passwordEncoder.matches(rawPassword, storedPassword);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
