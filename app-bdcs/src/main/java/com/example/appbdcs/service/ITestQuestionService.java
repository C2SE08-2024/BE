package com.example.appbdcs.service;

import com.example.appbdcs.model.TestQuestion;

import java.util.List;

public interface ITestQuestionService {
    List<TestQuestion> getQuestionsByTest(Integer testId);
}
