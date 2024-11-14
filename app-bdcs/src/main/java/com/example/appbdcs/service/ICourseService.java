package com.example.appbdcs.service;

import com.example.appbdcs.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    List<Course> findAll();

    Course createCourse(Course course);

    Optional<Course> updateCourse(Integer id, Course courseDetails);

    boolean deleteCourse(Integer id);

    Course findById(Integer id);

    Course save(Course course);

    void deleteById(Integer id);




}
