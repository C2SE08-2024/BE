package com.example.appbdcs.repository;

import com.example.appbdcs.model.StudentTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IStudentTestResultRepository extends JpaRepository<StudentTestResult, Integer> {

    @Query(value = "SELECT * FROM student_test_result str WHERE str.student_id = :studentId AND str.test_id = :testId",
            nativeQuery = true)
    List<StudentTestResult> findByStudentIdAndTestId(Integer studentId, Integer testId);

    List<StudentTestResult> findTestResultsByStudent(Integer studentId);

    // Tìm điểm của học sinh theo ID học sinh
    @Query(value = "SELECT * FROM student_test_result WHERE student_id = :studentId", nativeQuery = true)
    List<StudentTestResult> findResultsByStudentId(@Param("studentId") Integer studentId);

    // Tìm điểm của học sinh theo ID bài kiểm tra
    @Query(value = "SELECT * FROM student_test_result WHERE test_id = :testId", nativeQuery = true)
    List<StudentTestResult> findResultsByTestId(@Param("testId") Integer testId);

    // Tìm điểm của học sinh theo ID bài kiểm tra và ID học sinh
    @Query(value = "SELECT * FROM student_test_result WHERE student_id = :studentId AND test_id = :testId", nativeQuery = true)
    StudentTestResult findResultByStudentAndTest(@Param("studentId") Integer studentId, @Param("testId") Integer testId);

    // Cập nhật điểm cho học sinh theo bài kiểm tra
    @Query(value = "UPDATE student_test_result SET score = :score, is_passed = :isPassed WHERE student_id = :studentId AND test_id = :testId", nativeQuery = true)
    void updateStudentTestResult(@Param("studentId") Integer studentId,
                                 @Param("testId") Integer testId,
                                 @Param("score") Integer score,
                                 @Param("isPassed") Boolean isPassed);

    // Xóa kết quả bài kiểm tra của học sinh
    @Query(value = "DELETE FROM student_test_result WHERE student_id = :studentId AND test_id = :testId", nativeQuery = true)
    void deleteResult(@Param("studentId") Integer studentId, @Param("testId") Integer testId);
}
