package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.job.JobDTO;
import com.example.appbdcs.model.Job;
import com.example.appbdcs.repository.IJobRepository;
import com.example.appbdcs.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobService implements IJobService {

    @Autowired
    private IJobRepository jobRepository;

    @Override
    public JobDTO createJob(JobDTO jobDTO) {
        // Sử dụng query SQL thuần để tạo công việc
        jobRepository.createJob(
                jobDTO.getJobTitle(),
                jobDTO.getJobDescription(),
                jobDTO.getLocation(),
                jobDTO.getIndustry(),
                jobDTO.getRequirement(),
                jobDTO.getStatus(),
                jobDTO.getSalaryRange(),
                jobDTO.getJobType(),
                jobDTO.getPosterDate(),
                jobDTO.getExpiryDate(),
                jobDTO.getBusinessId()
        );
        return jobDTO; // Trả về đối tượng DTO (hoặc có thể truy vấn lại nếu cần)
    }

    @Override
    public JobDTO updateJob(Integer jobId, JobDTO jobDTO) {
        // Kiểm tra nếu job tồn tại trước khi cập nhật
        Optional<Job> existingJob = Optional.ofNullable(jobRepository.findJobById(jobId));
        if (existingJob.isPresent()) {
            jobRepository.updateJob(
                    jobId,
                    jobDTO.getJobTitle(),
                    jobDTO.getJobDescription(),
                    jobDTO.getLocation(),
                    jobDTO.getIndustry(),
                    jobDTO.getRequirement(),
                    jobDTO.getStatus(),
                    jobDTO.getSalaryRange(),
                    jobDTO.getJobType(),
                    jobDTO.getPosterDate(),
                    jobDTO.getExpiryDate(),
                    jobDTO.getBusinessId()
            );
            return jobDTO;
        }
        return null; // Trả về null nếu job không tồn tại
    }

    @Override
    public boolean deleteJob(Integer jobId) {
        // Kiểm tra nếu job tồn tại trước khi xóa
        if (jobRepository.findJobById(jobId) != null) {
            jobRepository.deleteJob(jobId);
            return true;
        }
        return false; // Trả về false nếu job không tồn tại
    }

    @Override
    public JobDTO getJobById(Integer jobId) {
        Job job = jobRepository.findJobById(jobId);
        if (job != null) {
            return mapToDTO(job);
        }
        return null; // Trả về null nếu job không tồn tại
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAllJobs();
        return jobs.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobDTO> searchJobsByTitle(String title) {
        List<Job> jobs = jobRepository.findJobsByTitle(title);
        return jobs.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Phương thức tiện ích để chuyển từ Job sang JobDTO
    private JobDTO mapToDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId(job.getJobId());
        jobDTO.setJobTitle(job.getJobTitle());
        jobDTO.setJobDescription(job.getJobDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setIndustry(job.getIndustry());
        jobDTO.setRequirement(job.getRequirement());
        jobDTO.setStatus(job.getStatus());
        jobDTO.setSalaryRange(job.getSalaryRange());
        jobDTO.setJobType(job.getJobType());
        jobDTO.setPosterDate(job.getPosterDate());
        jobDTO.setExpiryDate(job.getExpiryDate());
        if (job.getBusiness() != null) {
            jobDTO.setBusinessId(job.getBusiness().getBusinessId());
        }
        return jobDTO;
    }

    @Override
    public List<JobDTO> getJobsByBusinessId(Integer businessId) {
        List<Job> jobs = jobRepository.findJobsByBusinessId(businessId);
        return jobs.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
