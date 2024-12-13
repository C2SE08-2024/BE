package com.example.appbdcs.service;

import com.example.appbdcs.dto.job.JobDTO;

import java.util.List;

public interface IJobService {

    JobDTO createJob(JobDTO jobDTO);
    JobDTO updateJob(Integer jobId, JobDTO jobDTO);
    boolean deleteJob(Integer jobId);
    JobDTO getJobById(Integer jobId);
    List<JobDTO> getAllJobs();
    List<JobDTO> searchJobsByTitle(String title);
}
