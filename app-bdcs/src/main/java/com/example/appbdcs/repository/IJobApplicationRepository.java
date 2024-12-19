package com.example.appbdcs.repository;

import com.example.appbdcs.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IJobApplicationRepository extends JpaRepository<JobApplication, Integer> {

    // Tìm tất cả JobApplication của một Business
    @Query("SELECT ja FROM JobApplication ja WHERE ja.business.businessId = :businessId")
    List<JobApplication> findAllByBusinessId(Integer businessId);

    // Tìm tất cả JobApplication của một Student
    @Query("SELECT ja FROM JobApplication ja WHERE ja.student.studentId = :studentId")
    List<JobApplication> findAllByStudentId(Integer studentId);

    // Tìm tất cả JobApplication liên quan đến một Job
    @Query("SELECT ja FROM JobApplication ja WHERE ja.job.jobId = :jobId")
    List<JobApplication> findAllByJobId(Integer jobId);
}
