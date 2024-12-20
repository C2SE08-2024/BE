package com.example.appbdcs.service;

import com.example.appbdcs.dto.lesson.LessonDTO;

import java.util.List;

public interface ILessonService {
    List<LessonDTO> getLessonsByCourseId(Integer courseId);
}
