package com.example.appbdcs.controller;

import com.example.appbdcs.model.StudentProgress;
import com.example.appbdcs.service.IStudentProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student-progress")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentProgressController {

    @Autowired
    private IStudentProgressService studentProgressService;

    /**
     * API để cập nhật tiến độ học của học sinh
     *
     * @param studentId ID của học sinh
     * @param courseId ID của khóa học
     * @return Tiến độ học của học sinh sau khi được cập nhật
     */
    @PutMapping("/update/{studentId}/{courseId}")
    public ResponseEntity<StudentProgress> updateProgress(@PathVariable Integer studentId,
                                                          @PathVariable Integer courseId) {
        try {
            // Gọi service để cập nhật tiến độ
            StudentProgress updatedProgress = studentProgressService.updateProgress(studentId, courseId);
            return new ResponseEntity<>(updatedProgress, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            // Trả về lỗi khi không tìm thấy tiến độ hoặc có lỗi khác
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint để lấy tiến độ của học sinh trong một khóa học
    @GetMapping("/{studentId}/{courseId}")
    public ResponseEntity<?> getProgress(
            @PathVariable Integer studentId,
            @PathVariable Integer courseId) {
        try {
            // Lấy tiến độ học sinh từ service
            StudentProgress progress = studentProgressService.getProgress(studentId, courseId);
            return new ResponseEntity<>(progress, HttpStatus.OK);
        } catch (Exception e) {
            // Log chi tiết lỗi
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
