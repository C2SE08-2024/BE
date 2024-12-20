package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.test.CreateTestDTO;
import com.example.appbdcs.dto.test.CreateTestQuestionDTO;
import com.example.appbdcs.dto.test.TestDTO;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.model.Test;
import com.example.appbdcs.model.TestQuestion;
import com.example.appbdcs.repository.ICourseRepository;
import com.example.appbdcs.repository.ITestQuestionRepository;
import com.example.appbdcs.repository.ITestRepository;
import com.example.appbdcs.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService implements ITestService {

    @Autowired
    private ITestRepository testRepository;

    @Autowired
    private ITestQuestionRepository testQuestionRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<TestDTO> getTestsByCourse(Integer courseId) {
        List<Test> tests = testRepository.findByCourseId(courseId);
        return tests.stream()
                .map(test -> new TestDTO(
                        test.getTestId(),
                        test.getTestName(),
                        test.getProgressThreshold(),
                        test.getPassScore()
                )).collect(Collectors.toList());
    }

    @Override
    public CreateTestDTO createTestWithQuestions(CreateTestDTO createTestDTO) {
        Course course = courseRepository.findById(createTestDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + createTestDTO.getCourseId()));

        Test test = new Test();
        test.setTestName(createTestDTO.getTestName());
        test.setPassScore(createTestDTO.getPassScore());
        test.setProgressThreshold(createTestDTO.getProgressThreshold());
        test.setCourse(course);
        Test saveTest = testRepository.save(test);

        List<TestQuestion> savedQuestions = createTestDTO.getQuestions().stream().map(qDto -> {
            TestQuestion question = new TestQuestion();
            question.setQuestionContent(qDto.getQuestionContent());
            question.setCorrectAnswer(qDto.getCorrectAnswer());
            question.setTest(saveTest);
            return testQuestionRepository.save(question);
        }).collect(Collectors.toList());

        createTestDTO.setTestId(saveTest.getTestId());
        createTestDTO.setQuestions(savedQuestions.stream().map(q -> {
            CreateTestQuestionDTO questionDTO = new CreateTestQuestionDTO();
            questionDTO.setQuestionId(q.getQuestionId());
            questionDTO.setQuestionContent(q.getQuestionContent());
            questionDTO.setCorrectAnswer(q.getCorrectAnswer());
            return questionDTO;
        }).collect(Collectors.toList()));

        return createTestDTO;
    }
}
