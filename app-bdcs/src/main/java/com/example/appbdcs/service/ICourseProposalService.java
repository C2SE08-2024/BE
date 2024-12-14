package com.example.appbdcs.service;

import com.example.appbdcs.model.CourseProposal;

import java.util.Optional;

public interface ICourseProposalService {
    void save(CourseProposal courseProposal);

    Optional<CourseProposal> findCourseProposalById(Integer proposalId);
}
