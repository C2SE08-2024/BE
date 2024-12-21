package com.example.appbdcs.controller;

import com.example.appbdcs.model.StudentCv;
import com.example.appbdcs.service.IStudentCvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student-cvs")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentCVController {
    @Autowired
    private IStudentCvService studentCvService;

    // Lấy CV theo ID
    @GetMapping("/{cvId}")
    public ResponseEntity<StudentCv> getCvById(@PathVariable Integer cvId) {
        try {
            StudentCv studentCv = studentCvService.getCvById(cvId);
            return ResponseEntity.ok(studentCv);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null); // Trả về 404 nếu không tìm thấy
        }
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
