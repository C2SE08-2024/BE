package com.example.appbdcs.repository;

import com.example.appbdcs.model.StudentProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IStudentProgressRepository extends JpaRepository<StudentProgress, Integer> {

    @Query(value = "SELECT * FROM student_progress WHERE student_id = :studentId AND course_id = :courseId", nativeQuery = true)
    StudentProgress findByStudentIdAndCourseId(@Param("studentId") Integer studentId,
                                               @Param("courseId") Integer courseId);


}
