package com.example.appbdcs.service;

import com.example.appbdcs.dto.test.CreateTestDTO;
import com.example.appbdcs.dto.test.TestDTO;
import com.example.appbdcs.model.Test;

import java.util.List;

public interface ITestService {
    List<TestDTO> getTestsByCourse(Integer courseId);

    // Thêm bài test mới
    Test addTest(Test test);

    // Sửa bài test
    Test updateTest(Integer testId, Test test);

    // Xóa bài test
    boolean deleteTest(Integer testId);

    // Lấy tất cả bài test
    List<Test> getAllTests();

    // Lấy bài test theo ID
    Test getTestById(Integer testId);

    CreateTestDTO createTestWithQuestions(CreateTestDTO createTestDTO);


}
