package com.example.appbdcs.controller;

import com.example.appbdcs.model.TestQuestion;
import com.example.appbdcs.service.ITestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test-questions")
@CrossOrigin(origins = "http://localhost:4200")
public class TestQuestionController {

    @Autowired
    private ITestQuestionService testQuestionService;

    @GetMapping("/test/{testId}")
    public ResponseEntity<List<TestQuestion>> getQuestionsByTest(@PathVariable Integer testId) {
        List<TestQuestion> questions = testQuestionService.getQuestionsByTest(testId);
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(questions);
    }

    // Thêm TestQuestion
    @PostMapping("/add")
    public ResponseEntity<TestQuestion> addTestQuestion(@RequestBody TestQuestion testQuestion) {
        TestQuestion addedTestQuestion = testQuestionService.addTestQuestion(testQuestion);
        return ResponseEntity.ok(addedTestQuestion);
    }

    // Sửa TestQuestion
    @PutMapping("/{id}")
    public ResponseEntity<TestQuestion> updateTestQuestion(@PathVariable("id") Integer questionId,
                                                           @RequestBody TestQuestion updatedTestQuestion) {
        TestQuestion updated = testQuestionService.updateTestQuestion(questionId, updatedTestQuestion);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Xoá TestQuestion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestQuestion(@PathVariable("id") Integer questionId) {
        boolean deleted = testQuestionService.deleteTestQuestion(questionId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Lấy tất cả TestQuestions
    @GetMapping
    public ResponseEntity<List<TestQuestion>> getAllTestQuestions() {
        List<TestQuestion> testQuestions = testQuestionService.getAllTestQuestions();
        return ResponseEntity.ok(testQuestions);
    }

    // Lấy TestQuestion theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TestQuestion> getTestQuestionById(@PathVariable("id") Integer questionId) {
        TestQuestion testQuestion = testQuestionService.getTestQuestionById(questionId);
        if (testQuestion != null) {
            return ResponseEntity.ok(testQuestion);
        }
        return ResponseEntity.notFound().build();
    }
}
