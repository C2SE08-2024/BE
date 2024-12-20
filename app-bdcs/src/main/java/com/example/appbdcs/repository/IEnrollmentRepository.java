package com.example.appbdcs.repository;

import com.example.appbdcs.model.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IEnrollmentRepository extends JpaRepository<Enrollments, Integer> {
}
