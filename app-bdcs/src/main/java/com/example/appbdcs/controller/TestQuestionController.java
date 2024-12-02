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
}
