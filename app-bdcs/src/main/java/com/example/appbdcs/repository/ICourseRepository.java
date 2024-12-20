package com.example.appbdcs.repository;

import com.example.appbdcs.dto.course.PopularCourseDTO;
import com.example.appbdcs.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
