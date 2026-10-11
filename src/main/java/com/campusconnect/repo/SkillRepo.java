package com.campusconnect.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusconnect.entity.Skill;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {

    List<Skill> findByStudentId(Long studentId);

}