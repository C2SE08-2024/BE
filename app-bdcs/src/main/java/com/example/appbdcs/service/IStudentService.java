package com.example.appbdcs.service;

import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.dto.student.StudentUserDetailDto;
import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.model.StudentCv;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    void save(Student student);

    Student studentLimit();

    List<Student> findAllStudents();

    List<Student> findAllWithPagination(int size, int offset);

    Student findStudentByCode(String studentCode);

    List<Student> findStudentsByTest(Integer testId);

    List<Student> getStudentsInCourse(Integer courseId);

    StudentDTO findById(Integer studentId);

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO updateStudent(Integer studentId, StudentDTO studentDTO);

    void deleteStudent(Integer studentId);

    Student findStudentByUsername(String username);

    StudentUserDetailDto findUserDetailByUsername(String username);

    StudentDTO convertToDTO(Student student);

    boolean existsById(Integer studentId);

}

