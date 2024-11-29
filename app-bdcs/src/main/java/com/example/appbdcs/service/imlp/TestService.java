package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Test;
import com.example.appbdcs.repository.ITestRepository;
import com.example.appbdcs.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService implements ITestService {

    @Autowired
    private ITestRepository testRepository;

    @Override
    public List<Test> getTestsByCourse(Integer courseId) {
        return testRepository.findByCourseId(courseId);
    }
}
