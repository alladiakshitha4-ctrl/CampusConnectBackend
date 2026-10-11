package com.campusconnect.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusconnect.entity.Marks;

@Repository
public interface MarksRepo extends JpaRepository<Marks, Long> {

    List<Marks> findByStudentId(Long studentId);

    Page<Marks> findByStudentId(
            Long studentId,
            Pageable pageable
    );

    Page<Marks> findByCourseId(
            Long courseId,
            Pageable pageable
    );

    Page<Marks> findByStudentIdAndCourseId(
            Long studentId,
            Long courseId,
            Pageable pageable
    );
}