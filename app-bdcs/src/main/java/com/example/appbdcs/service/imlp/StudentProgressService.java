package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Lesson;
import com.example.appbdcs.model.StudentProgress;
import com.example.appbdcs.model.StudentTestResult;
import com.example.appbdcs.model.Test;
import com.example.appbdcs.repository.ILessonRepository;
import com.example.appbdcs.repository.IStudentProgressRepository;
import com.example.appbdcs.repository.IStudentTestResultRepository;
import com.example.appbdcs.repository.ITestRepository;
import com.example.appbdcs.service.IStudentProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProgressService implements IStudentProgressService {


    @Autowired
    private IStudentProgressRepository studentProgressRepository;

    @Autowired
    private IStudentTestResultRepository studentTestResultRepository;

    @Autowired
    private ILessonRepository lessonRepository;

    @Autowired
    private ITestRepository testRepository;

    @Override
    public StudentProgress updateProgress(Integer studentId, Integer courseId) {
        StudentProgress progress = studentProgressRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (progress == null) {
            throw new RuntimeException("Student progress not found for studentId " + studentId + "and courseId " + courseId);
        }

        List<Lesson> lessons = lessonRepository.findLessonsByCourseId(courseId);
        int totalLessons = lessons.size();

        int completedLessons = 0;
        for (Lesson lesson: lessons) {
            if (lesson.isCompletedByStudent(studentId)) {
                completedLessons++;
            }
        }

        List<Test> tests = testRepository.findByCourseId(courseId);
        int completedTests = 0;
        for (Test test: tests) {
            List<StudentTestResult> results = studentTestResultRepository.findByStudentIdAndTestId(studentId, test.getTestId());
            if (!results.isEmpty() && results.get(0).getScore() >= test.getPassScore()) {
                completedTests++;
            }
        }

        int totalTasks = totalLessons + tests.size();
        int completedTasks = completedLessons + completedTests;

        int progressPercentage = (completedTasks * 100) / totalTasks;

        progress.setProgressPercentage(progressPercentage);
        progress.setProgressStatus(progressPercentage >= 100);

        return studentProgressRepository.save(progress);
    }

}
