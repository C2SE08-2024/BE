package com.example.appbdcs.controller;

import com.example.appbdcs.model.StudentTestResult;
import com.example.appbdcs.service.IStudentTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class StudentTestResultController {

    @Autowired
    private IStudentTestResultService studentTestResultService;

    @PostMapping("/submit/{studentId}/{testId}")
    public ResponseEntity<StudentTestResult> submitTest(@PathVariable Integer studentId, @PathVariable Integer testId,
                                                        @RequestBody List<String> studentAnswers) {
        StudentTestResult result = studentTestResultService.submitTest(studentId, testId, studentAnswers);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}
