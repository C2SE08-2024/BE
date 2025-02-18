package com.example.appbdcs.controller;

import com.example.appbdcs.dto.test.CreateTestDTO;
import com.example.appbdcs.dto.test.TestDTO;
import com.example.appbdcs.model.Test;
import com.example.appbdcs.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // Lấy tất cả bài test
    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    // Lấy bài test theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable("id") Integer id) {
        Test test = testService.getTestById(id);
        if (test != null) {
            return new ResponseEntity<>(test, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Nếu không tìm thấy
    }

    // Thêm bài test mới
    @PostMapping("/add")
    public ResponseEntity<Test> addTest(@RequestBody Test test) {
        Test createdTest = testService.addTest(test);
        return new ResponseEntity<>(createdTest, HttpStatus.CREATED);
    }

    // Cập nhật bài test
    @PutMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable("id") Integer id, @RequestBody Test test) {
        Test updatedTest = testService.updateTest(id, test);
        if (updatedTest != null) {
            return new ResponseEntity<>(updatedTest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Nếu không tìm thấy bài test
    }

    // Xoá bài test
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable("id") Integer id) {
        boolean isDeleted = testService.deleteTest(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Xoá thành công
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Nếu không tìm thấy bài test
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
