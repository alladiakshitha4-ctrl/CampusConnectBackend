package com.campusconnect.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusconnect.entity.Faculty;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmployeeId(String employeeId);

    Page<Faculty> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Optional<Faculty> findByEmail(String email);
}