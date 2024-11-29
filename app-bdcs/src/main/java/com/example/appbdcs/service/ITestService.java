package com.example.appbdcs.service;

import com.example.appbdcs.model.Test;

import java.util.List;

public interface ITestService {
    List<Test> getTestsByCourse(Integer courseId);
}
