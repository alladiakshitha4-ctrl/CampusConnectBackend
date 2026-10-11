package com.campusconnect.dto;

public class MarksResponseDTO {

    private Long id;
    private double marksObtained;
    private double totalMarks;
    private String examType;

    private Long studentId;
    private String studentName;

    private Long courseId;
    private String courseName;

    private double percentage;

    public MarksResponseDTO() {
    }

    public MarksResponseDTO(Long id,
                            double marksObtained,
                            double totalMarks,
                            String examType,
                            Long studentId,
                            String studentName,
                            Long courseId,
                            String courseName,
                            double percentage) {

        this.id = id;
        this.marksObtained = marksObtained;
        this.totalMarks = totalMarks;
        this.examType = examType;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}