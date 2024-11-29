package com.example.appbdcs.repository;

import com.example.appbdcs.model.StudentTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IStudentTestResultRepository extends JpaRepository<StudentTestResult, Integer> {
}
