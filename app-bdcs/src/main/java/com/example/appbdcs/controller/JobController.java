package com.example.appbdcs.controller;

import com.example.appbdcs.dto.job.JobDTO;
import com.example.appbdcs.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {

    @Autowired
    private IJobService jobService;

    // Tạo công việc mới
    @PostMapping
    public ResponseEntity<JobDTO> createJob(@Valid @RequestBody JobDTO jobDTO) {
        JobDTO createdJob = jobService.createJob(jobDTO);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    // Cập nhật công việc
    @PutMapping("/{jobId}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable Integer jobId, @Valid @RequestBody JobDTO jobDTO) {
        JobDTO updatedJob = jobService.updateJob(jobId, jobDTO);
        if (updatedJob != null) {
            return new ResponseEntity<>(updatedJob, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Trả về 404 nếu không tìm thấy công việc
    }

    // Xóa công việc
    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer jobId) {
        boolean isDeleted = jobService.deleteJob(jobId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Trả về 204 nếu xóa thành công
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Trả về 404 nếu không tìm thấy công việc
    }

    // Lấy thông tin công việc theo ID
    @GetMapping("/{jobId}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Integer jobId) {
        JobDTO jobDTO = jobService.getJobById(jobId);
        if (jobDTO != null) {
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Trả về 404 nếu không tìm thấy công việc
    }

    // Lấy tất cả công việc
    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        List<JobDTO> jobDTOs = jobService.getAllJobs();
        return new ResponseEntity<>(jobDTOs, HttpStatus.OK);
    }

    // Tìm kiếm công việc theo tên
    @GetMapping("/search")
    public ResponseEntity<List<JobDTO>> searchJobsByTitle(@RequestParam String title) {
        List<JobDTO> jobDTOs = jobService.searchJobsByTitle(title);
        return new ResponseEntity<>(jobDTOs, HttpStatus.OK);
    }

    @GetMapping("/business/{businessId}")
    public ResponseEntity<List<JobDTO>> getJobsByBusinessId(@PathVariable Integer businessId) {
        List<JobDTO> jobs = jobService.getJobsByBusinessId(businessId);
        if (!jobs.isEmpty()) {
            return ResponseEntity.ok(jobs);
        }
        return ResponseEntity.noContent().build();
    }
}
