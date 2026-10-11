package com.campusconnect.dto;

public class PerformanceAnalyticsDTO {

    private Long studentId;
    private String studentName;

    private double marksPercentage;
    private double attendancePercentage;
    private double overallScore;

    private String performanceLevel;
    private String riskLevel;

    public PerformanceAnalyticsDTO() {
    }

    public PerformanceAnalyticsDTO(Long studentId,
                                   String studentName,
                                   double marksPercentage,
                                   double attendancePercentage,
                                   double overallScore,
                                   String performanceLevel,
                                   String riskLevel) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.marksPercentage = marksPercentage;
        this.attendancePercentage = attendancePercentage;
        this.overallScore = overallScore;
        this.performanceLevel = performanceLevel;
        this.riskLevel = riskLevel;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getMarksPercentage() {
        return marksPercentage;
    }

    public void setMarksPercentage(double marksPercentage) {
        this.marksPercentage = marksPercentage;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public String getPerformanceLevel() {
        return performanceLevel;
    }

    public void setPerformanceLevel(String performanceLevel) {
        this.performanceLevel = performanceLevel;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}