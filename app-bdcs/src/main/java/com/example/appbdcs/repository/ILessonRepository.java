package com.example.appbdcs.repository;

import com.example.appbdcs.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ILessonRepository extends JpaRepository<Lesson, Integer> {

    @Query(value = "SELECT * FROM lesson l WHERE l.course_id = :courseId", nativeQuery = true)
    List<Lesson> findByCourseId(Integer courseId);
}
