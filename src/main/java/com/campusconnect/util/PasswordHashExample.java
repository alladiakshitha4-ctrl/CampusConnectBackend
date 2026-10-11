
package com.campusconnect.util;

import com.campusconnect.entity.Student;
import com.campusconnect.entity.Faculty;
import com.campusconnect.repo.StudentRepo;
import com.campusconnect.repo.FacultyRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("reset-test-passwords")
public class PasswordHashExample implements CommandLineRunner {

    private final StudentRepo studentRepo;
    private final FacultyRepo facultyRepo;
    private final PasswordEncoder encoder;

    public PasswordHashExample(
            StudentRepo studentRepo,
            FacultyRepo facultyRepo,
            PasswordEncoder encoder) {
        this.studentRepo = studentRepo;
        this.facultyRepo = facultyRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        studentRepo.findByEmail("teststudent@gmail.com")
            .ifPresent(s -> {
                s.setPassword(encoder.encode("Student@123"));
                studentRepo.save(s);
            });

        facultyRepo.findByEmail("facultytest9@campusconnect.com")
            .ifPresent(f -> {
                f.setPassword(encoder.encode("Faculty@123"));
                facultyRepo.save(f);
            });

String studentPassword = encoder.encode("Student@123");

studentRepo.findByEmail("rahul@gmail.com")
    .ifPresent(s -> {
        s.setPassword(studentPassword);
        studentRepo.save(s);
    });

studentRepo.findByEmail("anurag133@gmail.com")
    .ifPresent(s -> {
        s.setPassword(studentPassword);
        studentRepo.save(s);
    });

studentRepo.findByEmail("teststudent@gmail.com")
    .ifPresent(s -> {
        s.setPassword(studentPassword);
        studentRepo.save(s);
    });

studentRepo.findByEmail("logintest@gmail.com")
    .ifPresent(s -> {
        s.setPassword(studentPassword);
        studentRepo.save(s);
    });

studentRepo.findByEmail("securitytest@gmail.com")
    .ifPresent(s -> {
        s.setPassword(studentPassword);
        studentRepo.save(s);
    });

studentRepo.findByEmail("facultytest@campusconnect.com")
    .ifPresent(s -> {
        s.setPassword(studentPassword);
        studentRepo.save(s);
    });


        System.out.println("TEST PASSWORDS RESET COMPLETE");
    }
}
