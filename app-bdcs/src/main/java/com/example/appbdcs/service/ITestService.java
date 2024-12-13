package com.example.appbdcs.service;

import com.example.appbdcs.model.Test;

import java.util.List;
import java.util.Optional;

public interface ITestService {
    List<Test> getTestsByCourse(Integer courseId);

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
}
