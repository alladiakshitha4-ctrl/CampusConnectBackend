package com.campusconnect.dto;

import java.time.LocalDate;

public class AttendanceResponseDTO {

    private Long id;
    private LocalDate attendanceDate;
    private boolean present;

    private Long studentId;
    private String studentName;

    private Long courseId;
    private String courseName;

    public AttendanceResponseDTO() {
    }

    public AttendanceResponseDTO(
            Long id,
            LocalDate attendanceDate,
            boolean present,
            Long studentId,
            String studentName,
            Long courseId,
            String courseName) {

        this.id = id;
        this.attendanceDate = attendanceDate;
        this.present = present;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
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
}