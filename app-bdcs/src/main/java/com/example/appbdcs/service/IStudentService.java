package com.example.appbdcs.service;

import com.example.appbdcs.model.Student;

public interface IStudentService {
    void save(Student student);

    Student studentLimit();

    Student findStudentByUsername(String username);
}
