package com.example.appbdcs.repository;

import com.example.appbdcs.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ICourseRepository extends JpaRepository<Course, Integer> {

    // Tìm khóa học theo tên
    @Query(value = "SELECT * FROM course WHERE course_name = :name", nativeQuery = true)
    List<Course> findByName(@Param("name") String name);

    // Tìm khóa học có tên chứa một chuỗi cụ thể (tìm kiếm gần đúng)
    @Query(value = "SELECT * FROM course WHERE course_name LIKE %:keyword%", nativeQuery = true)
    List<Course> findByNameContaining(@Param("keyword") String keyword);

}
