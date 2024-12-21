package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.*;
import com.example.appbdcs.repository.*;
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

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public StudentProgress updateProgress(Integer studentId, Integer courseId) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        StudentProgress progress = studentProgressRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (progress == null) {
            progress = new StudentProgress();
            progress.setStudent(student);
            progress.setCourse(course);
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

            // Tính tổng số công việc và số công việc đã hoàn thành
            int totalTasks = totalLessons + tests.size();
            int completedTasks = completedLessons + completedTests;

            // Tính phần trăm tiến độ
            int progressPercentage = (totalTasks == 0) ? 0 : (completedTasks * 100) / totalTasks;

            // Cập nhật tiến độ và trạng thái tiến độ
            progress.setProgressPercentage(progressPercentage);
            progress.setProgressStatus(progressPercentage >= 100); // Đánh dấu là hoàn thành khi đạt 100%

            // Lưu tiến độ học sinh vào cơ sở dữ liệu
            return studentProgressRepository.save(progress);
        }


        int totalTasks = totalLessons + tests.size();
        int completedTasks = completedLessons + completedTests;

        int progressPercentage = (completedTasks * 100) / totalTasks;

        progress.setProgressPercentage(progressPercentage);
        progress.setProgressStatus(progressPercentage >= 100);

        return studentProgressRepository.save(progress);
    }

    @Override
    public StudentProgress getProgress(Integer studentId, Integer courseId) {
        // Tìm tiến độ học sinh trong khóa học
        StudentProgress progress = studentProgressRepository.findByStudentIdAndCourseId(studentId, courseId);

        if (progress == null) {
            throw new RuntimeException("Progress not found for studentId " + studentId + " and courseId " + courseId);
        }

        return progress;
    }

}
