package com.example.appbdcs.service;

import com.example.appbdcs.dto.course.CourseDTO;
import com.example.appbdcs.dto.course.PopularCourseDTO;
import com.example.appbdcs.model.Course;

import java.util.List;

public interface ICourseService {
    List<Course> findAll();

    void save(Course course);

    Course findCourseById(Integer id);

    List<PopularCourseDTO> getMostPopularCourses();

    List<Course> getPaidCourses();

    List<Course> getFreeCourses();

    Course createCourse(Course course);

    Course updateCourse(Integer courseId, CourseDTO updatedCourseDTO);

    void deleteCourse(Integer courseId);
}
