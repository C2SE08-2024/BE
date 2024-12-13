package com.example.appbdcs.repository;

import com.example.appbdcs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
@Transactional
public interface IJobRepository extends JpaRepository<Job, Integer> {

        List<Job> findByJobTitleContainingIgnoreCase(String title);

    @Modifying
    @Query(value = "INSERT INTO job (job_title, job_description, location, industry, requirement, status, salary_range, job_type, poster_date, expiry_date, business_id) " +
            "VALUES (:jobTitle, :jobDescription, :location, :industry, :requirement, :status, :salaryRange, :jobType, :posterDate, :expiryDate, :businessId)", nativeQuery = true)
    void createJob(@Param("jobTitle") String jobTitle,
                   @Param("jobDescription") String jobDescription,
                   @Param("location") String location,
                   @Param("industry") String industry,
                   @Param("requirement") String requirement,
                   @Param("status") String status,
                   @Param("salaryRange") String salaryRange,
                   @Param("jobType") String jobType,
                   @Param("posterDate") Date posterDate,
                   @Param("expiryDate") Date expiryDate,
                   @Param("businessId") Integer businessId);

    @Modifying
    @Query(value = "UPDATE job SET job_title = :jobTitle, job_description = :jobDescription, location = :location, industry = :industry, " +
            "requirement = :requirement, status = :status, salary_range = :salaryRange, job_type = :jobType, poster_date = :posterDate, " +
            "expiry_date = :expiryDate, business_id = :businessId WHERE job_id = :jobId", nativeQuery = true)
    void updateJob(@Param("jobId") Integer jobId,
                   @Param("jobTitle") String jobTitle,
                   @Param("jobDescription") String jobDescription,
                   @Param("location") String location,
                   @Param("industry") String industry,
                   @Param("requirement") String requirement,
                   @Param("status") String status,
                   @Param("salaryRange") String salaryRange,
                   @Param("jobType") String jobType,
                   @Param("posterDate") Date posterDate,
                   @Param("expiryDate") Date expiryDate,
                   @Param("businessId") Integer businessId);


    @Modifying
    @Query(value = "DELETE FROM job WHERE job_id = :jobId", nativeQuery = true)
    void deleteJob(@Param("jobId") Integer jobId);


    @Query(value = "SELECT * FROM job WHERE job_id = :jobId", nativeQuery = true)
    Job findJobById(@Param("jobId") Integer jobId);


    @Query(value = "SELECT * FROM job", nativeQuery = true)
    List<Job> findAllJobs();


    @Query(value = "SELECT * FROM job WHERE job_title LIKE %:title%", nativeQuery = true)
    List<Job> findJobsByTitle(@Param("title") String title);


}
