package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Lesson;
import com.example.appbdcs.repository.ILessonRepository;
import com.example.appbdcs.service.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService implements ILessonService {

    @Autowired
    private ILessonRepository lessonRepository;

    @Override
    public List<Lesson> getLessonsByCourseId(Integer courseId) {
        return lessonRepository.findByCourseId(courseId);
    }
}
