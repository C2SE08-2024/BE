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

    // 1. Lấy tất cả các bài học
    @Query(value = "SELECT * FROM Lesson", nativeQuery = true)
    List<Lesson> findAllLessons();

    // 2. Lấy bài học theo ID
    @Query(value = "SELECT * FROM Lesson WHERE lesson_id = :lessonId", nativeQuery = true)
    Lesson findLessonById(@Param("lessonId") Integer lessonId);

    // 3. Lấy tất cả các bài học thuộc một khoá học
    @Query(value = "SELECT * FROM Lesson WHERE course_id = :courseId", nativeQuery = true)
    List<Lesson> findLessonsByCourseId(@Param("courseId") Integer courseId);

    // 4. Lấy tất cả các bài học có độ dài bài học lớn hơn một giá trị nhất định
    @Query(value = "SELECT * FROM Lesson WHERE lesson_duration > :duration", nativeQuery = true)
    List<Lesson> findLessonsByDurationGreaterThan(@Param("duration") String duration);

    @Modifying
    @Query(value = "DELETE FROM Lesson WHERE lesson_id = :lessonId", nativeQuery = true)
    void deleteLessonById(@Param("lessonId") Integer lessonId);

    @Modifying
    @Query(value = "INSERT INTO Lesson (lesson_name, lesson_content, video, lesson_duration, course_id, test_id) " +
            "VALUES (:lessonName, :lessonContent, :video, :lessonDuration, :courseId, :testId)", nativeQuery = true)
    void insertLesson(@Param("lessonName") String lessonName,
                      @Param("lessonContent") String lessonContent,
                      @Param("video") String video,
                      @Param("lessonDuration") String lessonDuration,
                      @Param("courseId") Integer courseId,
                      @Param("testId") Integer testId);


    @Modifying
    @Query(value = "UPDATE Lesson SET lesson_name = :lessonName, lesson_content = :lessonContent, " +
            "video = :video, lesson_duration = :lessonDuration, course_id = :courseId, test_id = :testId " +
            "WHERE lesson_id = :lessonId", nativeQuery = true)
    void updateLesson(@Param("lessonId") Integer lessonId,
                      @Param("lessonName") String lessonName,
                      @Param("lessonContent") String lessonContent,
                      @Param("video") String video,
                      @Param("lessonDuration") String lessonDuration,
                      @Param("courseId") Integer courseId,
                      @Param("testId") Integer testId);


    @Query(value = "SELECT student_id FROM lesson_completed_students WHERE lesson_id = :lessonId", nativeQuery = true)
    List<Integer> findCompletedStudentsByLessonId(@Param("lessonId") Integer lessonId);

    @Query(value = "SELECT * FROM lesson l WHERE l.course_id = :courseId", nativeQuery = true)
    List<Lesson> findByCourseId(Integer courseId);
}