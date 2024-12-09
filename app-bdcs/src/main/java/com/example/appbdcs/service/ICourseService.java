package com.example.appbdcs.service;

import com.example.appbdcs.dto.course.CourseDTO;
import com.example.appbdcs.dto.course.PopularCourseDTO;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.model.Student;

import java.util.List;

public interface ICourseService {
    List<Course> findAll();

    Course findCourseById(Integer id);

    List<PopularCourseDTO> getMostPopularCourses();

    List<Course> getPaidCourses();

    List<Course> getFreeCourses();

    Course createCourse(Course course);

    Course updateCourse(Integer courseId, CourseDTO updatedCourseDTO);

    void deleteCourse(Integer courseId);

    List<Student> getStudentsByCourseId(Integer courseId);

    Course getCourseById(Integer courseId);
}
