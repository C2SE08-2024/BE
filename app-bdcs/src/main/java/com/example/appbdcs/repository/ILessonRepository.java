package com.example.appbdcs.repository;

import com.example.appbdcs.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ILessonRepository extends JpaRepository<Lesson, Integer> {

    @Query(value = "SELECT * FROM lesson l WHERE l.course_id = :courseId", nativeQuery = true)
    List<Lesson> findByCourseId(Integer courseId);

    @Modifying
    @Query(value = "INSERT INTO lesson (lesson_name, lesson_content, video, lesson_duration, course_id, test_id) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void createLesson(String lessonName, String lessonContent, String video, String lessonDuration,
                      Integer courseId, Integer testId);
    @Modifying
    @Query(value = "UPDATE lesson SET lesson_name = ?1, lesson_content = ?2, video = ?3, lesson_duration = ?4, " +
            "course_id = ?5, test_id = ?6 WHERE lesson_id = ?7", nativeQuery = true)
    void updateLesson(String lessonName, String lessonContent, String video, String lessonDuration,
                      Integer courseId, Integer testId, Integer lessonId);

    @Modifying
    @Query(value = "DELETE FROM lesson WHERE lesson_id = ?1", nativeQuery = true)
    void deleteLessonById(Integer lessonId);

    @Query(value = "SELECT * FROM lesson WHERE lesson_id = :lessonId", nativeQuery = true)
    Optional<Lesson> findByLessonId(@Param("lessonId") Integer lessonId);



}
