package com.campusconnect.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusconnect.entity.Notice;

@Repository
public interface NoticeRepo extends JpaRepository<Notice, Long> {

    Page<Notice> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(
            String title,
            String content,
            Pageable pageable);

    Page<Notice> findByCategoryContainingIgnoreCase(
            String category,
            Pageable pageable);

    Page<Notice> findByActive(
            boolean active,
            Pageable pageable);
}