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

    //hiện thị chi tiết tất cả khoá học theo ID
    @Query(value = "SELECT * FROM course WHERE course_id = :id", nativeQuery = true)
    Optional<Course> findCourseById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE course SET course_name = :courseName, course_price = :coursePrice, duration = :duration, update_at = NOW() WHERE course_id = :id", nativeQuery = true)
    int updateCourse(@Param("id") Integer id,
                     @Param("courseName") String courseName,
                     @Param("coursePrice") Integer coursePrice,
                     @Param("duration") String duration);


    // Xóa khóa học bằng MySQL native query
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM course WHERE course_id = :id", nativeQuery = true)
    int deleteCourseById(@Param("id") Integer id);

    // Thêm khóa học mới bằng native query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO course (course_name, course_price, duration, create_at, update_at, category_id, instructor_id) " +
            "VALUES (:courseName, :coursePrice, :duration, NOW(), NOW(), :categoryId, :instructorId)", nativeQuery = true)
    int createCourse(@Param("courseName") String courseName,
                     @Param("coursePrice") Integer coursePrice,
                     @Param("duration") String duration,
                     @Param("categoryId") Integer categoryId,
                     @Param("instructorId") Integer instructorId);

}


