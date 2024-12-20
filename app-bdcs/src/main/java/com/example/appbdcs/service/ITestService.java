package com.example.appbdcs.service;

import com.example.appbdcs.dto.test.CreateTestDTO;
import com.example.appbdcs.dto.test.TestDTO;

import java.util.List;

public interface ITestService {
    List<TestDTO> getTestsByCourse(Integer courseId);

    CreateTestDTO createTestWithQuestions(CreateTestDTO createTestDTO);
}
