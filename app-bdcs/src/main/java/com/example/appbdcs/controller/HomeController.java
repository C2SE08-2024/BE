package com.example.appbdcs.controller;

import com.example.appbdcs.dto.course.PopularCourseDTO;
import com.example.appbdcs.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

    @Autowired
    private ICourseService courseService;

    @GetMapping("/popular")
    public ResponseEntity<List<PopularCourseDTO>> getMostPopularCourses() {
        List<PopularCourseDTO> popularCourses = courseService.getMostPopularCourses();
        if (popularCourses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(popularCourses);
    }
}
