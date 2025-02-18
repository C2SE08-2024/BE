package com.example.appbdcs.controller;

import com.example.appbdcs.dto.lesson.LessonDTO;

import com.example.appbdcs.dto.lesson.LessonDTO;
import com.example.appbdcs.model.Lesson;
import com.example.appbdcs.service.ILessonService;
import com.example.appbdcs.service.imlp.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lessons")
@CrossOrigin(origins = "http://localhost:4200")
public class LessonController {
    @Autowired
    private ILessonService lessonService;

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody LessonDTO lessonDTO) {
        Lesson createdLesson = lessonService.createLesson(lessonDTO);
        return ResponseEntity.ok(createdLesson);
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Integer lessonId, @RequestBody LessonDTO lessonDTO) {
        Lesson updatedLesson = lessonService.updateLesson(lessonId, lessonDTO);
        return ResponseEntity.ok(updatedLesson);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Integer lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Integer lessonId) {
        Lesson lesson = lessonService.getLessonById(lessonId);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Lesson>> getLessonsByCourseId(@PathVariable Integer courseId) {
        List<Lesson> lessons = lessonService.getLessonsByCourseId(courseId);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{lessonId}/completed-students")
    public ResponseEntity<List<Integer>> getCompletedStudentsByLessonId(@PathVariable Integer lessonId) {
        List<Integer> studentIds = lessonService.getCompletedStudentsByLessonId(lessonId);
        return ResponseEntity.ok(studentIds);
    }
}