package com.example.appbdcs.service;

import com.example.appbdcs.model.StudentCv;

import java.util.List;

public interface IStudentCvService {

    StudentCv getCvById(Integer cvId);

    List<StudentCv> getAllCvsByStudentId(Integer studentId);

    List<StudentCv> getAllCvsByBusinessId(Integer businessId);

    List<StudentCv> getAllCvsByType(String type);


}
