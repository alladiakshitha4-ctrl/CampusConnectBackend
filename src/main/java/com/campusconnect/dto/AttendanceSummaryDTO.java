package com.campusconnect.dto;

public class AttendanceSummaryDTO {

    private Long studentId;
    private String studentName;

    private Long courseId;
    private String courseName;

    private long totalClasses;
    private long classesPresent;
    private long classesAbsent;

    private double attendancePercentage;

    public AttendanceSummaryDTO() {
    }

    public AttendanceSummaryDTO(
            Long studentId,
            String studentName,
            Long courseId,
            String courseName,
            long totalClasses,
            long classesPresent,
            long classesAbsent,
            double attendancePercentage) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.totalClasses = totalClasses;
        this.classesPresent = classesPresent;
        this.classesAbsent = classesAbsent;
        this.attendancePercentage = attendancePercentage;
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

    public long getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(long totalClasses) {
        this.totalClasses = totalClasses;
    }

    public long getClassesPresent() {
        return classesPresent;
    }

    public void setClassesPresent(long classesPresent) {
        this.classesPresent = classesPresent;
    }

    public long getClassesAbsent() {
        return classesAbsent;
    }

    public void setClassesAbsent(long classesAbsent) {
        this.classesAbsent = classesAbsent;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}