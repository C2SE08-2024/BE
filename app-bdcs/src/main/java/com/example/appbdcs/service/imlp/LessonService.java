package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.lesson.LessonDTO;
import com.example.appbdcs.model.Lesson;
import com.example.appbdcs.repository.ILessonRepository;
import com.example.appbdcs.service.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class LessonService implements ILessonService {

    @Autowired
    private ILessonRepository lessonRepository;

    @Autowired
    private CourseService courseService;  // Assuming CourseService exists
    @Autowired
    private TestService testService;  // Assuming TestService exists

    /**
     * Create a new lesson
     */
    public Lesson createLesson(LessonDTO lessonDTO) {
        lessonRepository.insertLesson(
                lessonDTO.getLessonName(),
                lessonDTO.getLessonContent(),
                lessonDTO.getVideo(),
                lessonDTO.getLessonDuration(),
                lessonDTO.getCourseId(),
                lessonDTO.getTestId()
        );
        // Retrieve the newly created lesson for verification
        return lessonRepository.findAllLessons()
                .stream()
                .filter(lesson -> lesson.getLessonName().equals(lessonDTO.getLessonName()) &&
                        lesson.getLessonContent().equals(lessonDTO.getLessonContent()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Lesson creation failed"));
    }

    /**
     * Update an existing lesson
     */
    public Lesson updateLesson(Integer lessonId, LessonDTO lessonDTO) {
        Optional<Lesson> existingLesson = Optional.ofNullable(lessonRepository.findLessonById(lessonId));

        if (existingLesson.isPresent()) {
            lessonRepository.updateLesson(
                    lessonId,
                    lessonDTO.getLessonName(),
                    lessonDTO.getLessonContent(),
                    lessonDTO.getVideo(),
                    lessonDTO.getLessonDuration(),
                    lessonDTO.getCourseId(),
                    lessonDTO.getTestId()
            );
            return lessonRepository.findLessonById(lessonId);
        } else {
            throw new RuntimeException("Lesson not found with ID: " + lessonId);
        }
    }

    /**
     * Delete a lesson by ID
     */
    public void deleteLesson(Integer lessonId) {
        Optional<Lesson> existingLesson = Optional.ofNullable(lessonRepository.findLessonById(lessonId));

        if (existingLesson.isPresent()) {
            lessonRepository.deleteLessonById(lessonId);
        } else {
            throw new RuntimeException("Lesson not found with ID: " + lessonId);
        }
    }

    /**
     * Retrieve all lessons
     */
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAllLessons();
    }

    /**
     * Retrieve a lesson by ID
     */
    public Lesson getLessonById(Integer lessonId) {
        return Optional.ofNullable(lessonRepository.findLessonById(lessonId))
                .orElseThrow(() -> new RuntimeException("Lesson not found with ID: " + lessonId));
    }

    /**
     * Retrieve all lessons by course ID
     */
    public List<Lesson> getLessonsByCourseId(Integer courseId) {
        return lessonRepository.findLessonsByCourseId(courseId);
    }

    /**
     * Retrieve all students who completed a lesson
     */
    public List<Integer> getCompletedStudentsByLessonId(Integer lessonId) {
        return lessonRepository.findCompletedStudentsByLessonId(lessonId);
    }

    /**
     * Lấy thông tin bài học cùng với bài test liên quan (nếu có)
     *
     * @param lessonId ID của bài học
     * @return Thông tin bài học và bài test liên quan
     */
    public Map<String, Object> getLessonWithTest(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with ID: " + lessonId));

        Map<String, Object> response = new HashMap<>();
        response.put("lesson", lesson);

        // Nếu bài học có bài test liên quan, thêm bài test vào kết quả
        if (lesson.getTest() != null) {
            response.put("test", lesson.getTest());
        } else {
            response.put("test", "No test associated with this lesson.");
        }

        return response;
    }
}
