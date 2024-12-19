package com.example.appbdcs.service;

import com.example.appbdcs.dto.instructor.InstructorDTO;
import com.example.appbdcs.dto.instructor.InstructorUserDetailDto;
import com.example.appbdcs.model.Course;
import com.example.appbdcs.model.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IInstructorService {
    void save(Instructor instructor);

    Instructor instructorLimit();

    Instructor addInstructor(InstructorDTO instructorDTO);

    Instructor updateInstructor(Integer id, InstructorDTO instructorDTO);

    void deleteById(Integer id);

    Instructor findById(Integer id);

    Page<Instructor> searchInstructors(String name, String email, int page, int size);

    List<Instructor> findAll();

    InstructorUserDetailDto findUserDetailByUsername(String username);

    Optional<Instructor> findInstructorById(Integer instructorId);

    List<Course> findCoursesByInstructorId(Integer instructorId);
}
