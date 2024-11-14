package com.example.appbdcs.service;

import com.example.appbdcs.dto.student.StudentInfo;
import com.example.appbdcs.model.Cart;
import com.example.appbdcs.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
    void save(Student student);

    Page<Student> findAllStudents(Pageable pageable);

    Page<Student> searchStudents(String type, String name, String address, String phone, Pageable pageable);

    Student studentLimit();

    void saveStudent(StudentInfo studentInfo);

    Student findById(Integer id);

    void updateStudent(StudentInfo studentInfo, Integer id);

    void deleteById(Integer id);

//    StudentUserDetailDto findUserDetailByUsername(String username);

    Student findByCart(Cart cart);
}
