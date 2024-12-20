package com.example.appbdcs.controller;

import com.example.appbdcs.dto.test.SubmitTestDTO;
import com.example.appbdcs.dto.test.TestResultDTO;
import com.example.appbdcs.service.IStudentTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
