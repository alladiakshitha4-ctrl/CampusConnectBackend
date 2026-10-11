
package com.campusconnect.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.campusconnect.entity.Student;
import com.campusconnect.entity.Faculty;
import com.campusconnect.repo.StudentRepo;
import com.campusconnect.repo.FacultyRepo;

@Component
@Profile("setup-test-passwords")
public class TestAccountPasswordSetup implements CommandLineRunner {

    private final StudentRepo studentRepo;
    private final FacultyRepo facultyRepo;
    private final PasswordEncoder passwordEncoder;
    public TestAccountPasswordSetup(
            StudentRepo studentRepo,
            FacultyRepo facultyRepo,
            PasswordEncoder passwordEncoder) {
        this.studentRepo = studentRepo;
        this.facultyRepo = facultyRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Faculty faculty = facultyRepo.findByEmail(
            "facultytest9@campusconnect.com"
        ).orElseThrow(() ->
            new IllegalStateException("Faculty test account not found")
        );

        faculty.setPassword(passwordEncoder.encode("Faculty@123"));
        faculty.setRole("FACULTY");
        facultyRepo.save(faculty);

        Student student = studentRepo.findByEmail(
            "teststudent@gmail.com"
        ).orElseThrow(() ->
            new IllegalStateException("Student test account not found")
        );

        student.setPassword(passwordEncoder.encode("Student@123"));
        student.setRole("STUDENT");
        studentRepo.save(student);

        System.out.println("TEST ACCOUNT PASSWORDS UPDATED SUCCESSFULLY");
    }
}
