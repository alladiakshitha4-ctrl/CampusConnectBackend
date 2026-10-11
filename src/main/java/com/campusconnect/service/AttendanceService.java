package com.campusconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusconnect.dto.AttendanceRequestDTO;
import com.campusconnect.dto.AttendanceResponseDTO;
import com.campusconnect.dto.AttendanceSummaryDTO;
import com.campusconnect.entity.Attendance;
import com.campusconnect.entity.Course;
import com.campusconnect.entity.Student;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repo.AttendanceRepo;
import com.campusconnect.repo.CourseRepo;
import com.campusconnect.repo.StudentRepo;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    // Add attendance
    public AttendanceResponseDTO addAttendance(
            AttendanceRequestDTO dto) {

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with ID "
                                + dto.getStudentId()
                                + " not found"
                        )
                );

        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID "
                                + dto.getCourseId()
                                + " not found"
                        )
                );

        Attendance attendance = new Attendance();

        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setPresent(dto.isPresent());
        attendance.setStudent(student);
        attendance.setCourse(course);

        Attendance savedAttendance =
                attendanceRepo.save(attendance);

        return convertToResponseDTO(savedAttendance);
    }

    // Get all attendance
    public Page<AttendanceResponseDTO> getAttendance(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return attendanceRepo.findAll(pageable)
                .map(this::convertToResponseDTO);
    }

    // Get attendance by student
    public Page<AttendanceResponseDTO> getAttendanceByStudent(
            Long studentId,
            int page,
            int size) {

        if (!studentRepo.existsById(studentId)) {
            throw new ResourceNotFoundException(
                    "Student with ID "
                    + studentId
                    + " not found"
            );
        }

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by("attendanceDate").descending()
                );

        return attendanceRepo
                .findByStudentId(studentId, pageable)
                .map(this::convertToResponseDTO);
    }

    // Get attendance by course
    public Page<AttendanceResponseDTO> getAttendanceByCourse(
            Long courseId,
            int page,
            int size) {

        if (!courseRepo.existsById(courseId)) {
            throw new ResourceNotFoundException(
                    "Course with ID "
                    + courseId
                    + " not found"
            );
        }

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by("attendanceDate").descending()
                );

        return attendanceRepo
                .findByCourseId(courseId, pageable)
                .map(this::convertToResponseDTO);
    }

    // Get attendance of one student for one course
    public Page<AttendanceResponseDTO> getStudentCourseAttendance(
            Long studentId,
            Long courseId,
            int page,
            int size) {

        if (!studentRepo.existsById(studentId)) {
            throw new ResourceNotFoundException(
                    "Student with ID "
                    + studentId
                    + " not found"
            );
        }

        if (!courseRepo.existsById(courseId)) {
            throw new ResourceNotFoundException(
                    "Course with ID "
                    + courseId
                    + " not found"
            );
        }

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by("attendanceDate").descending()
                );

        return attendanceRepo
                .findByStudentIdAndCourseId(
                        studentId,
                        courseId,
                        pageable
                )
                .map(this::convertToResponseDTO);
    }

    // Get attendance by ID
    public AttendanceResponseDTO getAttendanceById(Long id) {

        Attendance attendance =
                attendanceRepo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attendance with ID "
                                        + id
                                        + " not found"
                                )
                        );

        return convertToResponseDTO(attendance);
    }

    // Update attendance
    public AttendanceResponseDTO updateAttendance(
            Long id,
            AttendanceRequestDTO dto) {

        Attendance existingAttendance =
                attendanceRepo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attendance with ID "
                                        + id
                                        + " not found"
                                )
                        );

        Student student =
                studentRepo.findById(dto.getStudentId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student with ID "
                                        + dto.getStudentId()
                                        + " not found"
                                )
                        );

        Course course =
                courseRepo.findById(dto.getCourseId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course with ID "
                                        + dto.getCourseId()
                                        + " not found"
                                )
                        );

        existingAttendance.setAttendanceDate(
                dto.getAttendanceDate()
        );

        existingAttendance.setPresent(
                dto.isPresent()
        );

        existingAttendance.setStudent(student);
        existingAttendance.setCourse(course);

        Attendance updatedAttendance =
                attendanceRepo.save(existingAttendance);

        return convertToResponseDTO(updatedAttendance);
    }

    // Delete attendance
    public void deleteAttendance(Long id) {

        if (!attendanceRepo.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Attendance with ID "
                    + id
                    + " not found"
            );
        }

        attendanceRepo.deleteById(id);
    }

    // Convert Entity to Response DTO
    private AttendanceResponseDTO convertToResponseDTO(
            Attendance attendance) {

        Student student = attendance.getStudent();
        Course course = attendance.getCourse();

        return new AttendanceResponseDTO(
                attendance.getId(),
                attendance.getAttendanceDate(),
                attendance.isPresent(),
                student.getId(),
                student.getName(),
                course.getId(),
                course.getCourseName()
        );
    }
 // Get attendance summary for a student and course
    public AttendanceSummaryDTO getAttendanceSummary(
            Long studentId,
            Long courseId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with ID "
                                + studentId
                                + " not found"
                        )
                );

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID "
                                + courseId
                                + " not found"
                        )
                );

        long totalClasses =
                attendanceRepo.countByStudentIdAndCourseId(
                        studentId,
                        courseId
                );

        long classesPresent =
                attendanceRepo
                        .countByStudentIdAndCourseIdAndPresentTrue(
                                studentId,
                                courseId
                        );

        long classesAbsent =
                attendanceRepo
                        .countByStudentIdAndCourseIdAndPresentFalse(
                                studentId,
                                courseId
                        );

        double attendancePercentage = 0.0;

        if (totalClasses > 0) {
            attendancePercentage =
                    ((double) classesPresent / totalClasses) * 100;
        }

        return new AttendanceSummaryDTO(
                student.getId(),
                student.getName(),
                course.getId(),
                course.getCourseName(),
                totalClasses,
                classesPresent,
                classesAbsent,
                attendancePercentage
        );
    }
}