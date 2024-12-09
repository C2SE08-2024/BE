package com.example.appbdcs.service;

import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.model.Student;

import java.util.List;

public interface IStudentService {
    void save(Student student);

    Student studentLimit();

    // Lấy danh sách học sinh tham gia một khóa học
     List<Student> findStudentsByCourse(Integer courseId);

    // Lấy tất cả học sinh
    List<Student> findAllStudents();

    List<Student> getStudentsInCourse(Integer courseId);

    // Lấy học sinh theo mã học sinh
    Student findStudentByCode(String studentCode);

    // Lấy danh sách học sinh tham gia một bài kiểm tra
    List<Student> findStudentsByTest(Integer testId);

    StudentDTO findById(Integer studentId);

    }
