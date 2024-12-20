package com.example.appbdcs.repository;

import com.example.appbdcs.model.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ICourseReviewRepository extends JpaRepository<CourseReview, Integer> {
}
