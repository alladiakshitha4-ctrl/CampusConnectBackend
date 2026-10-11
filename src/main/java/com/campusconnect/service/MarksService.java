package com.campusconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.campusconnect.entity.Course;
import com.campusconnect.entity.Marks;
import com.campusconnect.entity.Student;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.repo.CourseRepo;
import com.campusconnect.repo.MarksRepo;
import com.campusconnect.repo.StudentRepo;

@Service
public class MarksService {

    @Autowired
    private MarksRepo marksRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;


    // =========================
    // ADD MARKS
    // =========================
    public Marks addMarks(Marks marks) {

        Student student = studentRepo.findById(
                marks.getStudent().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Student not found"
                )
        );

        Course course = courseRepo.findById(
                marks.getCourse().getId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Course not found"
                )
        );

        if (marks.getMarksObtained() < 0) {
            throw new IllegalArgumentException(
                    "Marks cannot be negative"
            );
        }

        if (marks.getMarksObtained() > marks.getTotalMarks()) {
            throw new IllegalArgumentException(
                    "Marks obtained cannot be greater than total marks"
            );
        }

        marks.setStudent(student);
        marks.setCourse(course);

        return marksRepo.save(marks);
    }


    // =========================
    // GET ALL MARKS
    // =========================
    public Page<Marks> getAllMarks(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return marksRepo.findAll(pageable);
    }


    // =========================
    // GET MARKS BY ID
    // =========================
    public Marks getMarksById(Long id) {

        return marksRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Marks with ID " + id + " not found"
                        )
                );
    }


    // =========================
    // GET MARKS BY STUDENT
    // =========================
    public Page<Marks> getMarksByStudent(
            Long studentId,
            int page,
            int size) {

        studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with ID " + studentId
                                        + " not found"
                        )
                );

        Pageable pageable = PageRequest.of(page, size);

        return marksRepo.findByStudentId(
                studentId,
                pageable
        );
    }


    // =========================
    // GET MARKS BY COURSE
    // =========================
    public Page<Marks> getMarksByCourse(
            Long courseId,
            int page,
            int size) {

        courseRepo.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID " + courseId
                                        + " not found"
                        )
                );

        Pageable pageable = PageRequest.of(page, size);

        return marksRepo.findByCourseId(
                courseId,
                pageable
        );
    }


    // =========================
    // GET MARKS BY STUDENT + COURSE
    // =========================
    public Page<Marks> getMarksByStudentAndCourse(
            Long studentId,
            Long courseId,
            int page,
            int size) {

        studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student with ID " + studentId
                                        + " not found"
                        )
                );

        courseRepo.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course with ID " + courseId
                                        + " not found"
                        )
                );

        Pageable pageable = PageRequest.of(page, size);

        return marksRepo.findByStudentIdAndCourseId(
                studentId,
                courseId,
                pageable
        );
    }


    // =========================
    // UPDATE MARKS
    // =========================
    public Marks updateMarks(Long id, Marks newMarks) {

        Marks existingMarks = marksRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Marks with ID " + id + " not found"
                        )
                );

        if (newMarks.getMarksObtained() < 0) {
            throw new IllegalArgumentException(
                    "Marks cannot be negative"
            );
        }

        if (newMarks.getMarksObtained()
                > newMarks.getTotalMarks()) {

            throw new IllegalArgumentException(
                    "Marks obtained cannot be greater than total marks"
            );
        }

        existingMarks.setMarksObtained(
                newMarks.getMarksObtained()
        );

        existingMarks.setTotalMarks(
                newMarks.getTotalMarks()
        );

        existingMarks.setExamType(
                newMarks.getExamType()
        );

        return marksRepo.save(existingMarks);
    }


    // =========================
    // DELETE MARKS
    // =========================
    public void deleteMarks(Long id) {

        Marks marks = marksRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Marks with ID " + id + " not found"
                        )
                );

        marksRepo.delete(marks);
    }


    // =========================
    // MARKS SUMMARY
    // =========================
    public double getStudentMarksPercentage(
            Long studentId) {

        var marksList =
                marksRepo.findByStudentId(studentId);

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
}