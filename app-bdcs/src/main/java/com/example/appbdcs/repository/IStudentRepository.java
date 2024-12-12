package com.example.appbdcs.repository;


import com.example.appbdcs.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IStudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from student order by `student_code` desc limit 1", nativeQuery = true)
    Student limitStudent();

    // Truy vấn lấy danh sách học sinh tham gia khóa học với MySQL thuần
    @Query(value = "SELECT * FROM student s " +
            "JOIN course_student cs ON s.student_id = cs.student_id " +
            "WHERE cs.course_id = :courseId", nativeQuery = true)
    List<Student> findStudentsByCourse(Integer courseId);

    // Truy vấn lấy tất cả học sinh
    @Query(value = "SELECT * FROM student", nativeQuery = true)
    List<Student> findAllStudents();

    // Truy vấn lấy học sinh theo mã học sinh
    @Query(value = "SELECT * FROM student WHERE student_code = :studentCode", nativeQuery = true)
    Student findStudentByCode(String studentCode);

    // Truy vấn lấy tất cả học sinh có điểm thi qua một bài kiểm tra cụ thể
    @Query(value = "SELECT s.* FROM student s " +
            "JOIN student_test_result str ON s.student_id = str.student_id " +
            "JOIN test t ON str.test_id = t.test_id " +
            "WHERE t.test_id = :testId", nativeQuery = true)
    List<Student> findStudentsByTest(Integer testId);

    // Tìm tất cả học sinh trong khóa học
    @Query(value = "SELECT s.* FROM student s " +
            "JOIN course_student cs ON s.student_id = cs.student_id " +
            "WHERE cs.course_id = :courseId", nativeQuery = true)
    List<Student> findStudentsInCourse(@Param("courseId") Integer courseId);

    Optional<Student> findById(Integer studentId);

    @Query(value = "SELECT s.* FROM student s JOIN account a ON s.account_id = a.account_id WHERE a.username = ?1",
            nativeQuery = true)
    Student findStudentByUsername(String username);
}
