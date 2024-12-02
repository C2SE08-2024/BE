package com.example.appbdcs.repository;

import com.example.appbdcs.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ITestRepository extends JpaRepository<Test, Integer> {

    @Query(value = "SELECT * FROM test WHERE course_id = :courseId", nativeQuery = true)
    List<Test> findByCourseId(Integer courseId);
}
