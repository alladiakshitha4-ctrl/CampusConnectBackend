package com.campusconnect.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.dto.PlacementReadinessDTO;
import com.campusconnect.entity.Skill;

@Service
public class PlacementService {

    @Autowired
    private SkillService skillService;

    public PlacementReadinessDTO calculateReadiness(
            Long studentId,
            String targetRole) {

        List<String> requiredSkills = getRequiredSkills(targetRole);

        List<Skill> studentSkills =
                skillService.getStudentSkills(studentId);

        List<String> studentSkillNames = new ArrayList<>();

        for (Skill skill : studentSkills) {
            studentSkillNames.add(
                    skill.getSkillName().toLowerCase());
        }

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String required : requiredSkills) {

            if (studentSkillNames.contains(
                    required.toLowerCase())) {

                matchedSkills.add(required);

            } else {

                missingSkills.add(required);
            }
        }

        double readiness =
                ((double) matchedSkills.size()
                / requiredSkills.size()) * 100;

        String recommendation;

        if (readiness >= 80) {

            recommendation =
                    "Excellent placement readiness. Keep improving practical skills.";

        } else if (readiness >= 60) {

            recommendation =
                    "Good progress. Improve the missing skills for better placement opportunities.";

        } else {

            recommendation =
                    "Placement readiness is low. Focus on the missing skills.";

        }

        return new PlacementReadinessDTO(
                studentId,
                targetRole,
                Math.round(readiness * 100.0) / 100.0,
                matchedSkills,
                missingSkills,
                recommendation);
    }

    private List<String> getRequiredSkills(String targetRole) {

        if (targetRole.equalsIgnoreCase(
                "Java Full Stack Developer")) {

            return Arrays.asList(
                    "Java",
                    "Spring Boot",
                    "React",
                    "SQL",
                    "DSA",
                    "Git"
            );
        }

        if (targetRole.equalsIgnoreCase(
                "Data Analyst")) {

            return Arrays.asList(
                    "Python",
                    "SQL",
                    "Excel",
                    "Power BI",
                    "Statistics"
            );
        }

        if (targetRole.equalsIgnoreCase(
                "Frontend Developer")) {

            return Arrays.asList(
                    "HTML",
                    "CSS",
                    "JavaScript",
                    "React",
                    "Git"
            );
        }

        return Arrays.asList(
                "Java",
                "SQL",
                "Git"
        );
    }
}