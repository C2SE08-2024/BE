package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.*;
import com.example.appbdcs.repository.*;
import com.example.appbdcs.service.IJobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobApplicationService implements IJobApplicationService {

    @Autowired
    private IJobApplicationRepository jobApplicationRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IBusinessRepository businessRepository;

    @Autowired
    private IStudentCvRepository studentCvRepository;

    @Autowired
    private IJobRepository jobRepository;

    public String submitCvToBusiness(StudentCv studentId, Integer businessId, Integer cvId, Integer jobId) {
        // Lấy Job từ cơ sở dữ liệu
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));

        // Tạo đối tượng JobApplication
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJob(job); // Liên kết với thực thể Job được quản lý
        jobApplication.setJobApplicationDate(LocalDate.now());
        jobApplication.setStatus("Pending");
        jobApplication.setStudentCv(studentId);

        // Các liên kết khác
        // ...

        // Lưu vào cơ sở dữ liệu
        jobApplicationRepository.save(jobApplication);

        return "CV submitted successfully!";
    }

    // Lấy tất cả JobApplication của một Business
    public List<JobApplication> getApplicationsByBusiness(Integer businessId) {
        return jobApplicationRepository.findAllByBusinessId(businessId);
    }

    // Lấy tất cả JobApplication của một Student
    public List<JobApplication> getApplicationsByStudent(Integer studentId) {
        return jobApplicationRepository.findAllByStudentId(studentId);
    }

    public String updateCvStatus(Integer jobApplicationId, String status) {
        // Lấy thông tin JobApplication từ database
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new RuntimeException("Job Application not found"));

        // Cập nhật trạng thái
        jobApplication.setStatus(status);

        // Lưu lại thay đổi
        jobApplicationRepository.save(jobApplication);

        return "CV status updated to: " + status;
    }

    // Lấy tất cả JobApplication mà Business đã nhận
    public List<JobApplication> getReceivedApplications(Integer businessId) {
        return jobApplicationRepository.findAllByBusinessId(businessId);
    }

    public List<JobApplication> getPendingApplicationsForBusiness(Integer businessId) {
        return jobApplicationRepository.findAllByBusinessId(businessId).stream()
                .filter(jobApp -> "Pending".equalsIgnoreCase(jobApp.getStatus()))
                .collect(Collectors.toList());
    }
}
