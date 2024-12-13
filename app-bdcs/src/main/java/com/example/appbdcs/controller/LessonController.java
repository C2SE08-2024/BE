package com.example.appbdcs.controller;


import com.example.appbdcs.dto.lesson.LessonDTO;
import com.example.appbdcs.model.Lesson;
import com.example.appbdcs.service.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lesson")
@CrossOrigin(origins = "http://localhost:4200")
public class LessonController {
    @Autowired
    private ILessonService lessonService;

    @PostMapping
    public ResponseEntity<Optional<Lesson>> createLesson(@RequestBody LessonDTO lessonDTO) {
        // Tạo bài học và trả về đối tượng bài học vừa tạo
        Optional<Lesson> lesson = lessonService.createLesson(lessonDTO);
        return ResponseEntity.ok(lesson);  // Trả về đối tượng Lesson vừa được tạo
    }

    // Endpoint cập nhật bài học
    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable("id") Integer lessonId,
                                               @RequestBody LessonDTO lessonDTO) {
        Lesson updatedLesson = lessonService.updateLesson(lessonId, lessonDTO);
        return ResponseEntity.ok(updatedLesson);
    }

    // Endpoint xóa bài học
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable("id") Integer lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();  // Trả về 204 No Content nếu xóa thành công
    }

    // Lấy bài học theo ID
    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Integer lessonId) {
        Optional<Lesson> lesson = lessonService.getLessonById(lessonId);

        return lesson.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
