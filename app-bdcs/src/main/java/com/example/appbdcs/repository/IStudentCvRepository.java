package com.example.appbdcs.repository;

import com.example.appbdcs.model.StudentCv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IStudentCvRepository extends JpaRepository<StudentCv, Integer> {

    @Query(value = "SELECT * FROM student_cv WHERE id = :cvId", nativeQuery = true)
    Optional<StudentCv> findById(@Param("cvId") Integer cvId);

    @Query(value = "SELECT sc.* FROM student_cv sc JOIN job_application ja ON sc.student_cv_id = ja.student_cv_id WHERE ja.business_id = :businessId", nativeQuery = true)
    List<StudentCv> findAllByBusinessId(@Param("businessId") Integer businessId);


    @Query(value = "SELECT * FROM student_cv WHERE student_id = :studentId", nativeQuery = true)
    List<StudentCv> findAllByStudentId(@Param("studentId") Integer studentId);


    @Query(value = "SELECT * FROM student_cv WHERE student_cv_type = :type", nativeQuery = true)
    List<StudentCv> findAllByType(@Param("type") String type);

}
