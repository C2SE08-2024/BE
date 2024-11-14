package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Course;
import com.example.appbdcs.repository.ICourseRepository;
import com.example.appbdcs.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
