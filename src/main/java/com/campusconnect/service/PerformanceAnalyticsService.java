package com.campusconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusconnect.dto.PerformanceAnalyticsDTO;
import com.campusconnect.entity.Attendance;
import com.campusconnect.entity.Marks;
import com.campusconnect.entity.Student;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repo.AttendanceRepo;
import com.campusconnect.repo.MarksRepo;
import com.campusconnect.repo.StudentRepo;

@Service
public class PerformanceAnalyticsService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MarksRepo marksRepo;

    @Autowired
    private AttendanceRepo attendanceRepo;

    public PerformanceAnalyticsDTO getStudentPerformance(Long studentId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Student with ID " + studentId + " not found"
                    )
                );

        List<Marks> marksList = marksRepo.findByStudentId(studentId);

        List<Attendance> attendanceList =
                attendanceRepo.findByStudentId(studentId);

        double marksPercentage = calculateMarksPercentage(marksList);

        double attendancePercentage =
                calculateAttendancePercentage(attendanceList);

        double overallScore =
                (marksPercentage * 0.70)
                + (attendancePercentage * 0.30);

        String performanceLevel =
                calculatePerformanceLevel(overallScore);

        String riskLevel =
                calculateRiskLevel(marksPercentage,
                                   attendancePercentage);

        return new PerformanceAnalyticsDTO(
                student.getId(),
                student.getName(),
                marksPercentage,
                attendancePercentage,
                overallScore,
                performanceLevel,
                riskLevel
        );
    }

    private double calculateMarksPercentage(List<Marks> marksList) {

        if (marksList.isEmpty()) {
            return 0;
        }

        double obtained = 0;
        double total = 0;

        for (Marks marks : marksList) {
            obtained += marks.getMarksObtained();
            total += marks.getTotalMarks();
        }

        if (total == 0) {
            return 0;
        }

        return (obtained / total) * 100;
    }

    private double calculateAttendancePercentage(
            List<Attendance> attendanceList) {

        if (attendanceList.isEmpty()) {
            return 0;
        }

        int present = 0;

        for (Attendance attendance : attendanceList) {

            if (attendance.isPresent()) {
                present++;
            }
        }

        return ((double) present / attendanceList.size()) * 100;
    }

    private String calculatePerformanceLevel(double score) {

        if (score >= 85) {
            return "Excellent";
        } else if (score >= 70) {
            return "Good";
        } else if (score >= 50) {
            return "Average";
        } else {
            return "At Risk";
        }
    }

    private String calculateRiskLevel(
            double marksPercentage,
            double attendancePercentage) {

        if (marksPercentage < 50 ||
            attendancePercentage < 75) {

            return "HIGH";
        }

        if (marksPercentage < 70 ||
            attendancePercentage < 80) {

            return "MEDIUM";
        }

        return "LOW";
    }
}