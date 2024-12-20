package com.example.appbdcs.service;

import com.example.appbdcs.dto.instructor.InstructorUserDetailDto;
import com.example.appbdcs.model.Instructor;

import java.util.Optional;

public interface IInstructorService {
    void save(Instructor instructor);

    Instructor instructorLimit();

    InstructorUserDetailDto findUserDetailByUsername(String username);

    Optional<Instructor> findInstructorById(Integer instructorId);
}
