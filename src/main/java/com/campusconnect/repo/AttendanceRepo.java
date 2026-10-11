package com.campusconnect.repo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.campusconnect.entity.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {

    Page<Attendance> findByStudentId(
            Long studentId,
            Pageable pageable
    );
    List<Attendance> findByStudentId(Long studentId);

    Page<Attendance> findByCourseId(
            Long courseId,
            Pageable pageable
    );

    Page<Attendance> findByStudentIdAndCourseId(
            Long studentId,
            Long courseId,
            Pageable pageable
    );

    long countByStudentIdAndCourseId(
            Long studentId,
            Long courseId
    );

    long countByStudentIdAndCourseIdAndPresentTrue(
            Long studentId,
            Long courseId
    );

    long countByStudentIdAndCourseIdAndPresentFalse(
            Long studentId,
            Long courseId
    );
}