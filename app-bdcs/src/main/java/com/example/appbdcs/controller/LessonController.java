package com.example.appbdcs.controller;

import com.example.appbdcs.model.Lesson;
import com.example.appbdcs.service.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons")
@CrossOrigin(origins = "http://localhost:4200")
public class LessonController {

    @Autowired
    private ILessonService lessonService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Lesson>> getLessonsByCourseId(@PathVariable("courseId") Integer courseId) {
        List<Lesson> lessons = lessonService.getLessonsByCourseId(courseId);
        if (lessons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    
}
