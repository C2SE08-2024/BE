package com.example.appbdcs.service;

import com.example.appbdcs.dto.course.PopularCourseDTO;
import com.example.appbdcs.model.Course;

import java.util.List;

public interface ICourseService {
    List<Course> findAll();

    Course findCourseById(Integer id);

    List<PopularCourseDTO> getMostPopularCourses();
}
