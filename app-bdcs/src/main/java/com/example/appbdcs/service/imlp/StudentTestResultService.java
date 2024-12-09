package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.StudentTestResult;
import com.example.appbdcs.model.TestQuestion;
import com.example.appbdcs.repository.IStudentTestResultRepository;
import com.example.appbdcs.repository.ITestQuestionRepository;
import com.example.appbdcs.service.IStudentTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTestResultService implements IStudentTestResultService {

    @Autowired
    private ITestQuestionRepository testQuestionRepository;

    @Autowired
    private IStudentTestResultRepository studentTestResultRepository;

    @Override
    public StudentTestResult submitTest(Integer studentId, Integer testId, List<String> studentAnswers) {
        List<TestQuestion> testQuestions = testQuestionRepository.findByTestId(testId);
        int score = 0;
        for (int i = 0; i < studentAnswers.size(); i++) {
            if (studentAnswers.get(i).equals(testQuestions.get(i).getCorrectAnswer())) {
                score++;
            }
        }

        boolean isPassed = score >= (testQuestions.size() * 0.75); // Tỷ lệ đậu là 75%
        StudentTestResult result = new StudentTestResult();
        result.setScore(score);
        result.setIsPassed(isPassed);

        return studentTestResultRepository.save(result);
    }

    @Override
    public List<StudentTestResult> findTestResultsByStudent(Integer studentId) {
        return studentTestResultRepository.findTestResultsByStudent(studentId);
    }

    @Override
    public List<StudentTestResult> findResultsByStudentId(Integer studentId) {
        return studentTestResultRepository.findResultsByStudentId(studentId);
    }

    @Override
    public List<StudentTestResult> findResultsByTestId(Integer testId) {
        return studentTestResultRepository.findResultsByTestId(testId);
    }

    @Override
    public StudentTestResult findResultByStudentAndTest(Integer studentId, Integer testId) {
        return studentTestResultRepository.findResultByStudentAndTest(studentId, testId);
    }

    @Override
    public void updateStudentTestResult(Integer studentId, Integer testId, Integer score, Boolean isPassed) {
        studentTestResultRepository.updateStudentTestResult(studentId, testId, score, isPassed);
    }

    @Override
    public void deleteResult(Integer studentId, Integer testId) {
        studentTestResultRepository.deleteResult(studentId, testId);
    }
}
