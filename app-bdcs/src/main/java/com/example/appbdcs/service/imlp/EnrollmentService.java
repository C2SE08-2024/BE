package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Enrollments;
import com.example.appbdcs.repository.IEnrollmentRepository;
import com.example.appbdcs.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EnrollmentService implements IEnrollmentService {

    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Override
    public boolean isStudentEnrolled(Integer courseId, Integer studentId) {
        Long count = enrollmentRepository.countStudentEnrollments(courseId, studentId);
        return count != null && count > 0;
    }

    @Override
    @Transactional
    public void saveEnrollment(Enrollments enrollment) {
        enrollmentRepository.saveEnrollment(
                enrollment.getCourse().getCourseId(),
                enrollment.getStudent().getStudentId(),
                enrollment.getEnrollmentDay(),
                enrollment.getStatus()
        );
    }
}
