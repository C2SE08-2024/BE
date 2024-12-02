package com.example.appbdcs.controller;

import com.example.appbdcs.dto.course.CourseDTO;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping("")
    public ResponseEntity<List<Course>> getAllCourse() {
        List<Course> courseList = this.courseService.findAll();
        if (courseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.findCourseById(id), HttpStatus.OK);
    }

    @GetMapping("/paid")
    public ResponseEntity<List<Course>> getPaidCourses() {
        List<Course> paidCourses = courseService.getPaidCourses();
        return new ResponseEntity<>(paidCourses, HttpStatus.OK);
    }

    @GetMapping("/free")
    public ResponseEntity<List<Course>> getFreeCourses() {
        List<Course> freeCourses = courseService.getFreeCourses();
        return new ResponseEntity<>(freeCourses, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = courseService.createCourse(course);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (SecurityException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<Course> updateCourse(@PathVariable("courseId") Integer courseId,
                                               @RequestBody CourseDTO updatedCourseDTO) {
        try {
            Course existingCourse = courseService.updateCourse(courseId, updatedCourseDTO);
            return new ResponseEntity<>(existingCourse, HttpStatus.OK);
        } catch (SecurityException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Integer courseId) {
        try {
            courseService.deleteCourse(courseId);
            return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
        } catch (SecurityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
