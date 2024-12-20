package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.test.SubmitTestDTO;
import com.example.appbdcs.dto.test.TestResultDTO;
import com.example.appbdcs.model.*;
import com.example.appbdcs.repository.*;
import com.example.appbdcs.service.IStudentTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentTestResultService implements IStudentTestResultService {

    @Autowired
    private ITestRepository testRepository;

    @Autowired
    private ITestQuestionRepository testQuestionRepository;

    @Autowired
    private IStudentTestResultRepository studentTestResultRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IStudentProgressRepository studentProgressRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    @Transactional
    public TestResultDTO submitTestAndGradeForUser(String username, SubmitTestDTO submitTestDTO) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found with username:" + username));

        Test test = testRepository.findById(submitTestDTO.getTestId()).orElseThrow(() -> new RuntimeException("Test not found"));
        List<TestQuestion> questions = testQuestionRepository.findByTestId(submitTestDTO.getTestId());

        int correctAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (i < submitTestDTO.getAnswers().size() &&
                    questions.get(i).getCorrectAnswer().equalsIgnoreCase(submitTestDTO.getAnswers().get(i))) {
                correctAnswers++;
            }
        }

        int score = (correctAnswers * 100) / questions.size();
        boolean isPassed = score >= test.getPassScore();

        StudentTestResult testResult = new StudentTestResult();
        testResult.setStudent(student);
        testResult.setTest(test);
        testResult.setScore(score);
        testResult.setIsPassed(isPassed);
        studentTestResultRepository.save(testResult);

        updateStudentProgress(student.getStudentId(), test.getCourse().getCourseId(), isPassed);

        TestResultDTO resultDTO = new TestResultDTO();
        resultDTO.setScore(score);
        resultDTO.setPassed(isPassed);
        return resultDTO;
    }

    private void updateStudentProgress(Integer studentId, Integer courseId, boolean isPassed) {
        if (!isPassed) return;

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        Course course = courseRepository.findCourseById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));


        StudentProgress progress = studentProgressRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (progress == null) {
            progress = new StudentProgress();
            progress.setStudent(student);
            progress.setCourse(course);
            progress.setCompletedLessons(0);
            progress.setTotalLesson(4); // Assuming 4 progress checkpoints (25%, 50%, 75%, 100%)
        }

        progress.setCompletedLessons(progress.getCompletedLessons() + 1);
        progress.setProgressPercentage((progress.getCompletedLessons() * 100) / progress.getTotalLesson());
        studentProgressRepository.save(progress);
    }
}
