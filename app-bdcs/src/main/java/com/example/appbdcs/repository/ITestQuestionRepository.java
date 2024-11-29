package com.example.appbdcs.repository;

import com.example.appbdcs.model.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
