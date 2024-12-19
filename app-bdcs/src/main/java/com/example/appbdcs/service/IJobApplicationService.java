package com.example.appbdcs.service;

import com.example.appbdcs.model.JobApplication;

import java.util.List;

public interface IJobApplicationService {

    String submitCvToBusiness(Integer studentId, Integer businessId, Integer cvId, Integer jobId);

    List<JobApplication> getApplicationsByBusiness(Integer businessId);

    List<JobApplication> getApplicationsByStudent(Integer studentId);

    String updateCvStatus(Integer jobApplicationId, String status);
}
