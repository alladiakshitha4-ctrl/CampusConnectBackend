package com.campusconnect.dto;

import java.util.List;

public class PlacementReadinessDTO {

    private Long studentId;
    private String targetRole;
    private double readinessPercentage;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private String recommendation;

    public PlacementReadinessDTO() {
    }

    public PlacementReadinessDTO(
            Long studentId,
            String targetRole,
            double readinessPercentage,
            List<String> matchedSkills,
            List<String> missingSkills,
            String recommendation) {

        this.studentId = studentId;
        this.targetRole = targetRole;
        this.readinessPercentage = readinessPercentage;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.recommendation = recommendation;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public double getReadinessPercentage() {
        return readinessPercentage;
    }

    public void setReadinessPercentage(double readinessPercentage) {
        this.readinessPercentage = readinessPercentage;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}