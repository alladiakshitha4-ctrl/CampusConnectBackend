
package com.campusconnect.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.campusconnect.entity.Admin;
import com.campusconnect.entity.Faculty;
import com.campusconnect.entity.Student;
import com.campusconnect.repo.AdminRepo;
import com.campusconnect.repo.FacultyRepo;
import com.campusconnect.repo.StudentRepo;

@Service
public class CampusUserDetailsService implements UserDetailsService {

    private final StudentRepo studentRepo;
    private final FacultyRepo facultyRepo;
    private final AdminRepo adminRepo;

    public CampusUserDetailsService(
            StudentRepo studentRepo,
            FacultyRepo facultyRepo,
            AdminRepo adminRepo) {
        this.studentRepo = studentRepo;
        this.facultyRepo = facultyRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // Check students first
        Student student = studentRepo.findByEmail(email).orElse(null);

        if (student != null) {
            return buildUser(
                    student.getEmail(),
                    student.getPassword(),
                    student.getRole(),
                    "STUDENT");
        }

        // Check faculty
        Faculty faculty = facultyRepo.findByEmail(email).orElse(null);

        if (faculty != null) {
            return buildUser(
                    faculty.getEmail(),
                    faculty.getPassword(),
                    faculty.getRole(),
                    "FACULTY");
        }

        // Check administrators
        Admin admin = adminRepo.findByEmail(email).orElse(null);

        if (admin != null) {
            return buildUser(
                    admin.getEmail(),
                    admin.getPassword(),
                    admin.getRole(),
                    "ADMIN");
        }

        throw new UsernameNotFoundException("User not found");
    }

    private UserDetails buildUser(
            String email,
            String password,
            String role,
            String defaultRole) {

        if (email == null || password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "User email or password is missing");
        }

        String normalizedRole =
                (role == null || role.isBlank())
                        ? defaultRole
                        : role.trim().toUpperCase();

        if (normalizedRole.startsWith("ROLE_")) {
            normalizedRole = normalizedRole.substring(5);
        }

        if (!normalizedRole.equals("ADMIN")
                && !normalizedRole.equals("FACULTY")
                && !normalizedRole.equals("STUDENT")) {
            throw new IllegalStateException("Invalid user role");
        }

        return User.withUsername(email)
                .password(password)
                .roles(normalizedRole)
                .build();
    }
}
