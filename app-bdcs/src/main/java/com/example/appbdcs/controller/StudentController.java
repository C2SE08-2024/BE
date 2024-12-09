package com.example.appbdcs.controller;

import com.example.appbdcs.model.Request;
import com.example.appbdcs.model.Student;
import com.example.appbdcs.model.StudentTestResult;
import com.example.appbdcs.service.IStudentService;
import com.example.appbdcs.service.IStudentTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IStudentTestResultService studentTestResultService;

    // Lấy tất cả học sinh
    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    // Lấy kết quả bài kiểm tra của học sinh theo ID học sinh
    @GetMapping("/{studentId}/test-results")
    public ResponseEntity<List<StudentTestResult>> getStudentTestResults(@PathVariable Integer studentId) {
        List<StudentTestResult> results = studentTestResultService.findResultsByStudentId(studentId);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }

    // Lấy kết quả bài kiểm tra cho học sinh cụ thể và bài kiểm tra cụ thể
    @GetMapping("/{studentId}/test/{testId}/result")
    public ResponseEntity<StudentTestResult> getResultByStudentAndTest(@PathVariable Integer studentId,
                                                                       @PathVariable Integer testId) {
        StudentTestResult result = studentTestResultService.findResultByStudentAndTest(studentId, testId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
