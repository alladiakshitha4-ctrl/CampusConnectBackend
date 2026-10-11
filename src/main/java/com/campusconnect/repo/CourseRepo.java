package com.campusconnect.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusconnect.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    boolean existsByCourseCode(String courseCode);

    Page<Course> findByCourseNameContainingIgnoreCase(
            String courseName,
            Pageable pageable
    );
}