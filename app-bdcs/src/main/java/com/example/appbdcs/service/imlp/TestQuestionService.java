package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.TestQuestion;
import com.example.appbdcs.repository.ITestQuestionRepository;
import com.example.appbdcs.service.ITestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestQuestionService implements ITestQuestionService {

    @Autowired
    private ITestQuestionRepository testQuestionRepository;

    @Override
    public List<TestQuestion> getQuestionsByTest(Integer testId) {
        return testQuestionRepository.findByTestId(testId);
    }
}
