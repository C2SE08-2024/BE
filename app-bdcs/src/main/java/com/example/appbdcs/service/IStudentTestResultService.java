package com.example.appbdcs.service;

import com.example.appbdcs.dto.test.SubmitTestDTO;
import com.example.appbdcs.dto.test.TestResultDTO;

public interface IStudentTestResultService {
    TestResultDTO submitTestAndGradeForUser(String username, SubmitTestDTO submitTestDTO);
}
