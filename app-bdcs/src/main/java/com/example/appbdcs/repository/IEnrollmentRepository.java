package com.example.appbdcs.repository;

import com.example.appbdcs.model.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Repository
@Transactional
public interface IEnrollmentRepository extends JpaRepository<Enrollments, Integer> {

    @Query(value = "SELECT COUNT(*) FROM course_student WHERE course_id = ?1 AND student_id = ?2", nativeQuery = true)
    Long countStudentEnrollments(Integer courseId, Integer studentId);

    @Modifying
    @Query(value = "INSERT INTO enrollments (course_id, student_id, enrollment_day, status) VALUES (?1, ?2, ?3, ?4)",
            nativeQuery = true)
    void saveEnrollment(Integer courseId, Integer studentId, Date enrollmentDay, Boolean status);
}
