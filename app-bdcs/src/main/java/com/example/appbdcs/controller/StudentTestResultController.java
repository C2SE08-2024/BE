package com.example.appbdcs.controller;

import com.example.appbdcs.dto.test.SubmitTestDTO;
import com.example.appbdcs.dto.test.TestResultDTO;
import com.example.appbdcs.model.StudentTestResult;
import com.example.appbdcs.service.IStudentTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/student-test-result")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentTestResultController {

    @Autowired
    private IStudentTestResultService studentTestResultService;

    @PostMapping("/submit-test")
    public ResponseEntity<TestResultDTO> submitTest(@RequestBody SubmitTestDTO submitTestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        TestResultDTO result = studentTestResultService.submitTestAndGradeForUser(username, submitTestDTO);
        return ResponseEntity.ok(result);
    }

    // Lấy tất cả kết quả của học sinh
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentTestResult>> getResultsByStudent(@PathVariable Integer studentId) {
        List<StudentTestResult> results = studentTestResultService.findResultsByStudentId(studentId);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }

    // Lấy tất cả kết quả của bài kiểm tra
    @GetMapping("/test/{testId}")
    public ResponseEntity<List<StudentTestResult>> getResultsByTest(@PathVariable Integer testId) {
        List<StudentTestResult> results = studentTestResultService.findResultsByTestId(testId);
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }

    // Lấy kết quả của học sinh cho một bài kiểm tra cụ thể
    @GetMapping("/student/{studentId}/test/{testId}")
    public ResponseEntity<StudentTestResult> getResultByStudentAndTest(@PathVariable Integer studentId,
                                                                       @PathVariable Integer testId) {
        StudentTestResult result = studentTestResultService.findResultByStudentAndTest(studentId, testId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    // Cập nhật điểm cho bài kiểm tra
    @PutMapping("/update")
    public ResponseEntity<String> updateTestResult(@RequestParam Integer studentId,
                                                   @RequestParam Integer testId,
                                                   @RequestParam Integer score,
                                                   @RequestParam Boolean isPassed) {
        studentTestResultService.updateStudentTestResult(studentId, testId, score, isPassed);
        return ResponseEntity.ok("Test result updated successfully");
    }

    // Xóa kết quả bài kiểm tra của học sinh
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteResult(@RequestParam Integer studentId,
                                               @RequestParam Integer testId) {
        studentTestResultService.deleteResult(studentId, testId);
        return ResponseEntity.ok("Test result deleted successfully");
    }
}
