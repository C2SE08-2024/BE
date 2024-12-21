package com.example.appbdcs.service;

import com.example.appbdcs.dto.test.SubmitTestDTO;
import com.example.appbdcs.dto.test.TestResultDTO;
import com.example.appbdcs.model.StudentTestResult;

import java.util.List;

public interface IStudentTestResultService {

    List<StudentTestResult> findTestResultsByStudent(Integer studentId);

    // Lấy kết quả bài kiểm tra của học sinh theo ID
    List<StudentTestResult> findResultsByStudentId(Integer studentId);

    // Lấy kết quả bài kiểm tra của một bài kiểm tra cụ thể
    List<StudentTestResult> findResultsByTestId(Integer testId);

    // Lấy kết quả của một học sinh cho một bài kiểm tra
    StudentTestResult findResultByStudentAndTest(Integer studentId, Integer testId);

    // Cập nhật điểm cho bài kiểm tra của học sinh
    void updateStudentTestResult(Integer studentId, Integer testId, Integer score, Boolean isPassed);

    // Xóa kết quả bài kiểm tra của học sinh
    void deleteResult(Integer studentId, Integer testId);

    TestResultDTO submitTestAndGradeForUser(String username, SubmitTestDTO submitTestDTO);
}
