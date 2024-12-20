package com.example.appbdcs.service;

import com.example.appbdcs.model.StudentTestResult;

import java.util.List;

public interface IStudentTestResultService {

    StudentTestResult submitTest(Integer studentId, Integer testId, List<String> studentAnswers);
}
