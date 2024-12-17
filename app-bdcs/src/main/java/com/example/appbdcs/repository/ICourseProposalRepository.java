package com.example.appbdcs.repository;

import com.example.appbdcs.model.CourseProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ICourseProposalRepository extends JpaRepository<CourseProposal, Integer> {

    @Query(value = "SELECT * FROM course_proposal c WHERE c.proposal_id = :proposalId", nativeQuery = true)
    Optional<CourseProposal> findByProposalId(@Param("proposalId") Integer proposalId);
}
