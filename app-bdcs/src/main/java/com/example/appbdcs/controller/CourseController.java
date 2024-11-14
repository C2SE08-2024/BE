package com.example.appbdcs.controller;

import com.example.appbdcs.model.Course;
import com.example.appbdcs.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}