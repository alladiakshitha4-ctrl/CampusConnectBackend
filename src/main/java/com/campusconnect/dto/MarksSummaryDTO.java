package com.campusconnect.dto;

public class MarksSummaryDTO {

    private Long studentId;
    private String studentName;

    private Long courseId;
    private String courseName;

    private long totalExams;

    private double totalMarksObtained;

    private double totalMaximumMarks;

    private double averagePercentage;

    public MarksSummaryDTO() {
    }

    public MarksSummaryDTO(Long studentId,
                           String studentName,
                           Long courseId,
                           String courseName,
                           long totalExams,
                           double totalMarksObtained,
                           double totalMaximumMarks,
                           double averagePercentage) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.totalExams = totalExams;
        this.totalMarksObtained = totalMarksObtained;
        this.totalMaximumMarks = totalMaximumMarks;
        this.averagePercentage = averagePercentage;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getTotalExams() {
        return totalExams;
    }

    public void setTotalExams(long totalExams) {
        this.totalExams = totalExams;
    }

    public double getTotalMarksObtained() {
        return totalMarksObtained;
    }

    public void setTotalMarksObtained(double totalMarksObtained) {
        this.totalMarksObtained = totalMarksObtained;
    }

    public double getTotalMaximumMarks() {
        return totalMaximumMarks;
    }

    public void setTotalMaximumMarks(double totalMaximumMarks) {
        this.totalMaximumMarks = totalMaximumMarks;
    }

    public double getAveragePercentage() {
        return averagePercentage;
    }

    public void setAveragePercentage(double averagePercentage) {
        this.averagePercentage = averagePercentage;
    }
}