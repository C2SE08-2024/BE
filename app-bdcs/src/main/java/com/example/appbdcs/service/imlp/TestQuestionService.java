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

    // Thêm TestQuestion
    @Override
    public TestQuestion addTestQuestion(TestQuestion testQuestion) {
        testQuestionRepository.addTestQuestion(testQuestion.getQuestionContent(),
                testQuestion.getCorrectAnswer(),
                testQuestion.getTest().getTestId());
        return testQuestion; // Hoặc bạn có thể return null, nếu không cần xác nhận đối tượng vừa thêm
    }

    // Sửa TestQuestion
    @Override
    public TestQuestion updateTestQuestion(Integer questionId, TestQuestion updatedTestQuestion) {
        testQuestionRepository.updateTestQuestion(questionId,
                updatedTestQuestion.getQuestionContent(),
                updatedTestQuestion.getCorrectAnswer(),
                updatedTestQuestion.getTest().getTestId());
        return updatedTestQuestion;
    }

    // Xoá TestQuestion
    @Override
    public boolean deleteTestQuestion(Integer questionId) {
        testQuestionRepository.deleteTestQuestion(questionId);
        return true; // Trả về true nếu đã xoá thành công
    }

    // Lấy tất cả TestQuestions
    @Override
    public List<TestQuestion> getAllTestQuestions() {
        return testQuestionRepository.getAllTestQuestions();
    }

    // Lấy TestQuestion theo ID
    @Override
    public TestQuestion getTestQuestionById(Integer questionId) {
        return testQuestionRepository.getTestQuestionById(questionId);
    }
}
