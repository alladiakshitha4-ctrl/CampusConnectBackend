package com.campusconnect.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusconnect.entity.Student;

@Repository
public interface StudentRepo
        extends JpaRepository<Student, Long> {

    Student findByEmailAndPassword(
            String email,
            String password
    );
    

    Optional<Student> findByEmail(
            String email
    );

    boolean existsByEmail(
            String email
    );

    Page<Student> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );
}