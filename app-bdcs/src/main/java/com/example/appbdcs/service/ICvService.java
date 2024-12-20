package com.example.appbdcs.service;

import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.model.Cv;

public interface ICvService {
    Cv createCvForStudent(StudentDTO student, String cvContent, String filePath, String cvType);
}
