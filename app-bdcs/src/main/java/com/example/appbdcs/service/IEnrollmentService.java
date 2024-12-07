package com.example.appbdcs.service;

import com.example.appbdcs.model.Enrollments;

public interface IEnrollmentService {
    boolean isStudentEnrolled(Integer courseId, Integer studentId);

    void saveEnrollment(Enrollments enrollment);
}
