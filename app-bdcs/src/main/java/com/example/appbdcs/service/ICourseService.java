package com.example.appbdcs.service;

import com.example.appbdcs.dto.course.CourseDTO;
import com.example.appbdcs.dto.course.CourseWithInstructorDTO;
import com.example.appbdcs.dto.course.PopularCourseDTO;
import com.example.appbdcs.dto.student.StudentsByCourseDTO;
import com.example.appbdcs.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> findAll();

    void save(Course course);

    Course findCourseById(Integer id);

    List<PopularCourseDTO> getMostPopularCourses();

    List<Course> getPaidCourses();

    List<Course> getFreeCourses();

    List<Course> searchCoursesByName(String courseName);

    Optional<CourseWithInstructorDTO> getCourseWithInstructor(Integer courseId);

    List<CourseWithInstructorDTO> getAllCoursesWithInstructor();

    List<StudentsByCourseDTO> getStudentsByCourse(Integer courseId);

    Course createCourse(Course course);

    Course updateCourse(Integer courseId, CourseDTO updatedCourseDTO);

    void deleteCourse(Integer courseId);
}
