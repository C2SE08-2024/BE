package com.example.appbdcs.repository;

import com.example.appbdcs.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ITestRepository extends JpaRepository<Test, Integer> {

    @Query(value = "SELECT * FROM test WHERE course_id = :courseId", nativeQuery = true)
    List<Test> findByCourseId(Integer courseId);

    // Thêm bài test (INSERT)
    @Modifying
    @Query(value = "INSERT INTO test (test_name, progress_threshold, pass_score, course_id) VALUES (:testName, :progressThreshold, :passScore, :courseId)", nativeQuery = true)
    void addTest(String testName, Integer progressThreshold, Integer passScore, Integer courseId);

    // Sửa bài test (UPDATE)
    @Modifying
    @Query(value = "UPDATE test SET test_name = :testName, progress_threshold = :progressThreshold, pass_score = :passScore, course_id = :courseId WHERE test_id = :testId", nativeQuery = true)
    void updateTest(Integer testId, String testName, Integer progressThreshold, Integer passScore, Integer courseId);

    // Xoá bài test (DELETE)
    @Modifying
    @Query(value = "DELETE FROM test WHERE test_id = :testId", nativeQuery = true)
    void deleteTest(Integer testId);

    // Lấy tất cả bài test
    @Query(value = "SELECT * FROM test", nativeQuery = true)
    List<Test> findAllTests();
}
