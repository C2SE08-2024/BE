package com.example.appbdcs.service;

import com.example.appbdcs.dto.lesson.LessonDTO;
import com.example.appbdcs.model.Lesson;

import java.util.List;
import java.util.Optional;

public interface ILessonService {
    Lesson createLesson(LessonDTO lessonDTO);
    Lesson updateLesson(Integer lessonId, LessonDTO lessonDTO);
    void deleteLesson(Integer lessonId);
    List<Lesson> getAllLessons();
    Lesson getLessonById(Integer lessonId);
    List<Lesson> getLessonsByCourseId(Integer courseId);
    List<Integer> getCompletedStudentsByLessonId(Integer lessonId);

}
