package com.example.appbdcs.service;

import com.example.appbdcs.dto.course.CourseDetailDTO;
import com.example.appbdcs.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    Course createCourse(CourseDetailDTO courseDetailDTO);
    Course updateCourse(Integer id, CourseDetailDTO courseDetailDTO);
    void deleteCourse(Integer id);
    Course getCourseById(Integer id);
    List<Course> getAllCourses();


}
