package com.example.appbdcs.controller;

import com.example.appbdcs.model.StudentCv;
import com.example.appbdcs.service.IStudentCvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student-cvs")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentCVController {
    @Autowired
    private IStudentCvService studentCvService;

    // API: Lấy CV theo ID
    @GetMapping("/{cvId}")
    public StudentCv getCvById(@PathVariable Integer cvId) {
        return studentCvService.getCvById(cvId);
    }

    // API: Lấy tất cả CV của một Student
    @GetMapping("/student/{studentId}")
    public List<StudentCv> getAllCvsByStudentId(@PathVariable Integer studentId) {
        return studentCvService.getAllCvsByStudentId(studentId);
    }

    // API: Lấy tất cả CV đã được nộp vào một Business
    @GetMapping("/business/{businessId}")
    public List<StudentCv> getAllCvsByBusinessId(@PathVariable Integer businessId) {
        return studentCvService.getAllCvsByBusinessId(businessId);
    }

    // API: Lấy tất cả CV theo loại CV (type)
    @GetMapping("/type/{type}")
    public List<StudentCv> getAllCvsByType(@PathVariable String type) {
        return studentCvService.getAllCvsByType(type);
    }
}
