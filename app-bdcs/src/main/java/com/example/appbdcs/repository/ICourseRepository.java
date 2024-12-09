package com.example.appbdcs.repository;

import com.example.appbdcs.model.Course;
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
public interface ICourseRepository extends JpaRepository<Course, Integer> {

    @Query(value = "SELECT * FROM course WHERE course_id = :id", nativeQuery = true)
    Optional<Course> findCourseById(@Param("id") Integer id);

    @Query(value = "SELECT c.course_id, c.course_name, c.course_price, c.image, c.status, COUNT(cs.student_id) AS student_count " +
            "FROM course c " +
            "LEFT JOIN course_student cs ON c.course_id = cs.course_id " +
            "GROUP BY c.course_id, c.course_name, c.course_price, c.image, c.status " +
            "ORDER BY student_count DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Object[]> findMostPopularCourses();

    @Query(value = "SELECT * FROM course WHERE course_price > 0 AND status = true", nativeQuery = true)
    List<Course> findPaidCourses();

    @Query(value = "SELECT * FROM course WHERE course_price = 0 AND status = true", nativeQuery = true)
    List<Course> findFreeCourses();

    @Modifying
    @Query(value = "UPDATE course SET course_name = :courseName, course_price = :coursePrice, image = :image, " +
            "status = :status, instructor_id = CASE WHEN :instructorId IS NOT NULL THEN :instructorId ELSE instructor_id END " +
            "WHERE course_id = :courseId", nativeQuery = true)
    void updateCourse(@Param("courseId") Integer courseId,
                     @Param("courseName") String courseName,
                     @Param("coursePrice") Integer coursePrice,
                     @Param("image") String image,
                     @Param("status") Boolean status,
                     @Param("instructorId") Integer instructorId);

    @Modifying
    @Query(value = "UPDATE course SET status = false WHERE course_id = :courseId", nativeQuery = true)
    void deleteCourseById(@Param("courseId") Integer courseId);

    Course findByCourseId(Integer courseId);

}
