package com.example.appbdcs.repository;

import com.example.appbdcs.model.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface ITestQuestionRepository extends JpaRepository<TestQuestion, Integer> {

    @Query(value = "SELECT * FROM test_question WHERE test_id = :testId", nativeQuery = true)
    List<TestQuestion> findByTestId(@Param("testId") Integer testId);

    // Thêm TestQuestion
    @Modifying
    @Query(value = "INSERT INTO test_question (question_content, correct_answer, test_id) " +
            "VALUES (:questionContent, :correctAnswer, :testId)", nativeQuery = true)
    void addTestQuestion(@Param("questionContent") String questionContent,
                         @Param("correctAnswer") String correctAnswer,
                         @Param("testId") Integer testId);

    // Sửa TestQuestion
    @Query(value = "UPDATE test_question SET question_content = :questionContent, " +
            "correct_answer = :correctAnswer, test_id = :testId " +
            "WHERE question_id = :questionId", nativeQuery = true)
    void updateTestQuestion(@Param("questionId") Integer questionId,
                            @Param("questionContent") String questionContent,
                            @Param("correctAnswer") String correctAnswer,
                            @Param("testId") Integer testId);

    // Xoá TestQuestion
    @Query(value = "DELETE FROM test_question WHERE question_id = :questionId", nativeQuery = true)
    void deleteTestQuestion(@Param("questionId") Integer questionId);

    // Lấy tất cả TestQuestions
    @Query(value = "SELECT * FROM test_question", nativeQuery = true)
    List<TestQuestion> getAllTestQuestions();

    // Lấy TestQuestion theo ID
    @Query(value = "SELECT * FROM test_question WHERE question_id = :questionId", nativeQuery = true)
    TestQuestion getTestQuestionById(@Param("questionId") Integer questionId);
}
