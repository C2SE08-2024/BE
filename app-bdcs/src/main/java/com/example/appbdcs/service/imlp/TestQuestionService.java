package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.test.TestQuestionDTO;
import com.example.appbdcs.model.TestQuestion;
import com.example.appbdcs.repository.ITestQuestionRepository;
import com.example.appbdcs.service.ITestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestQuestionService implements ITestQuestionService {

    @Autowired
    private ITestQuestionRepository testQuestionRepository;

    @Override
    public List<TestQuestionDTO> getQuestionsByTest(Integer testId) {
        List<TestQuestion> testQuestions = testQuestionRepository.findByTestId(testId);
        return testQuestions.stream()
                .map(testQuestion -> new TestQuestionDTO(
                        testQuestion.getQuestionId(),
                        testQuestion.getQuestionContent()
                )).collect(Collectors.toList());
    }
}
