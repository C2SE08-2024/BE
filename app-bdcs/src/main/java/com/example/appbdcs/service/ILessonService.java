package com.example.appbdcs.service;

import com.example.appbdcs.model.Lesson;

import java.util.List;

public interface ILessonService {
    List<Lesson> getLessonsByCourseId(Integer courseId);
}
