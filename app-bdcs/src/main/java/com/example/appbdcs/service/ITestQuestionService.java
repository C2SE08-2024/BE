package com.example.appbdcs.service;

import com.example.appbdcs.model.TestQuestion;

import java.util.List;

public interface ITestQuestionService {
    List<TestQuestion> getQuestionsByTest(Integer testId);

    TestQuestion addTestQuestion(TestQuestion testQuestion);

    TestQuestion updateTestQuestion(Integer questionId, TestQuestion updatedTestQuestion);

    boolean deleteTestQuestion(Integer questionId);

    List<TestQuestion> getAllTestQuestions();

    TestQuestion getTestQuestionById(Integer questionId);
}
