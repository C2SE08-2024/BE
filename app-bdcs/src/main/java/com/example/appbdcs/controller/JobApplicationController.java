package com.example.appbdcs.controller;

import com.example.appbdcs.model.JobApplication;
import com.example.appbdcs.service.IJobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/job-applications")
@CrossOrigin(origins = "http://localhost:4200")
public class JobApplicationController {

    @Autowired
    private IJobApplicationService jobApplicationService;

    // API: Student nộp CV cho Business
    @PostMapping("/submit")
    public String submitCv(
            @RequestParam Integer studentId,
            @RequestParam Integer businessId,
            @RequestParam Integer cvId,
            @RequestParam Integer jobId) {

        return jobApplicationService.submitCvToBusiness(studentId, businessId, cvId, jobId);
    }

    // API: Lấy tất cả JobApplication của một Business
    @GetMapping("/business/{businessId}")
    public List<JobApplication> getApplicationsByBusiness(@PathVariable Integer businessId) {
        return jobApplicationService.getApplicationsByBusiness(businessId);
    }

    // API: Lấy tất cả JobApplication của một Student
    @GetMapping("/student/{studentId}")
    public List<JobApplication> getApplicationsByStudent(@PathVariable Integer studentId) {
        return jobApplicationService.getApplicationsByStudent(studentId);
    }

    // API: Lấy danh sách các CV đang chờ duyệt cho Business
    @GetMapping("/business/{businessId}/pending")
    public List<JobApplication> getPendingApplications(@PathVariable Integer businessId) {
        return jobApplicationService.getApplicationsByBusiness(businessId).stream()
                .filter(jobApp -> "Pending".equalsIgnoreCase(jobApp.getStatus()))
                .collect(Collectors.toList());
    }

    // API: Cập nhật trạng thái của CV (Duyệt hoặc Từ chối)
    @PutMapping("/{jobApplicationId}/status")
    public String updateCvStatus(
            @PathVariable Integer jobApplicationId,
            @RequestParam String status) {
        return jobApplicationService.updateCvStatus(jobApplicationId, status);
    }
}
