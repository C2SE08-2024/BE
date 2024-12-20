package com.example.appbdcs.service;

import com.example.appbdcs.model.Instructor;

public interface IInstructorService {
    void save(Instructor instructor);

    Instructor instructorLimit();
}
