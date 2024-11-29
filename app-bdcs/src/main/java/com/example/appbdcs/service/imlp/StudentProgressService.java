package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Lesson;
import com.example.appbdcs.model.StudentProgress;
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

        List<Lesson> lessons = lessonRepository.findByCourseId(courseId);
        int totalLessons = lessons.size();

        int completedLessons = 0;
        for (Lesson lesson: lessons) {

        }

        return progress;
    }
}
