package com.example.appbdcs.controller;

import com.example.appbdcs.dto.test.CreateTestDTO;
import com.example.appbdcs.dto.test.TestDTO;
import com.example.appbdcs.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @Autowired
    private ITestService testService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TestDTO>> getTestsByCourse(@PathVariable Integer courseId) {
        List<TestDTO> tests = testService.getTestsByCourse(courseId);
        if (tests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tests);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<?> createTestWithQuestions(@RequestBody CreateTestDTO createTestDTO) {
        try {
            CreateTestDTO createdTest = testService.createTestWithQuestions(createTestDTO);
            return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating test: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
