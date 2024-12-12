package com.example.appbdcs.service;

import com.example.appbdcs.model.Lesson;

import java.util.List;

import com.example.appbdcs.dto.lesson.LessonDTO;
import com.example.appbdcs.model.Lesson;

import java.util.Optional;

public interface ILessonService {
    List<Lesson> getLessonsByCourseId(Integer courseId);
    Optional<Lesson> createLesson(LessonDTO lessonDTO);
    Lesson updateLesson(Integer lessonId, LessonDTO lessonDTO);
    void deleteLesson(Integer lessonId);
    Optional<Lesson> findByLessonId(Integer lessonId);
    Optional<Lesson> getLessonById(Integer lessonId);
}
