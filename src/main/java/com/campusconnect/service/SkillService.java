package com.campusconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.entity.Skill;
import com.campusconnect.repo.SkillRepo;

@Service
public class SkillService {

    @Autowired
    private SkillRepo skillRepo;

    public Skill addSkill(Skill skill) {
        return skillRepo.save(skill);
    }

    public List<Skill> getStudentSkills(Long studentId) {
        return skillRepo.findByStudentId(studentId);
    }

    public void deleteSkill(Long id) {
        skillRepo.deleteById(id);
    }
}