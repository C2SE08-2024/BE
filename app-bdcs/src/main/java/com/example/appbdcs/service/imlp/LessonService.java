package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.lesson.LessonDTO;
import com.example.appbdcs.model.Lesson;
import com.example.appbdcs.repository.ILessonRepository;
import com.example.appbdcs.service.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService implements ILessonService {

    @Autowired
    private ILessonRepository lessonRepository;

    @Override
    public List<LessonDTO> getLessonsByCourseId(Integer courseId) {
        List<Lesson> lessons = lessonRepository.findByCourseId(courseId);
        return lessons.stream()
                .map(lesson -> new LessonDTO(
                        lesson.getLessonId(),
                        lesson.getLessonName(),
                        lesson.getLessonContent(),
                        lesson.getVideo(),
                        lesson.getLessonDuration()
                )).collect(Collectors.toList());
    }
}
