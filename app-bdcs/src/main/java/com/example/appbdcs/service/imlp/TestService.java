package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Test;
import com.example.appbdcs.repository.ITestRepository;
import com.example.appbdcs.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService implements ITestService {

    @Autowired
    private ITestRepository testRepository;

    @Override
    public List<Test> getTestsByCourse(Integer courseId) {
        return testRepository.findByCourseId(courseId);
    }

    public Test getTestById(Integer testId) {
        // Tìm kiếm test theo ID
        Optional<Test> test = testRepository.findById(testId);
        if (test.isPresent()) {
            return test.get();
        } else {
            throw new RuntimeException("Test not found with id: " + testId);
        }
    }
}
