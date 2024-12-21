package com.example.appbdcs.service;

import com.example.appbdcs.model.StudentProgress;

public interface IStudentProgressService {

    StudentProgress updateProgress(Integer studentId, Integer courseId);
    StudentProgress getProgress(Integer studentId, Integer courseId);
}
