package com.example.appbdcs.service;

import com.example.appbdcs.dto.test.TestQuestionDTO;
import com.example.appbdcs.model.TestQuestion;

import java.util.List;

public interface ITestQuestionService {
    List<TestQuestionDTO> getQuestionsByTest(Integer testId);
}
