package com.example.appbdcs.service;

import com.example.appbdcs.dto.job.CreateCvRequest;
import com.example.appbdcs.dto.student.StudentDTO;
import com.example.appbdcs.model.Cv;
import com.example.appbdcs.model.Education;
import com.example.appbdcs.model.Experience;

import java.util.List;

public interface ICvService {
    Cv createCv(
            Integer studentId,
            String name,
            String position,
            String summary,
            List<String> skills,
            List<Education> education,
            List<Experience> experiences
    );
}