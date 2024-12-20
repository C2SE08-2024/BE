package com.example.appbdcs.repository;

import com.example.appbdcs.model.StudentTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IStudentTestResultRepository extends JpaRepository<StudentTestResult, Integer> {

    @Query(value = "SELECT * FROM student_test_result str WHERE str.student_id = :studentId AND str.test_id = :testId",
            nativeQuery = true)
    List<StudentTestResult> findByStudentIdAndTestId(Integer studentId, Integer testId);
}
