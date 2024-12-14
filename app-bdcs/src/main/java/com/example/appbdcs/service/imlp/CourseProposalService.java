package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.CourseProposal;
import com.example.appbdcs.repository.ICourseProposalRepository;
import com.example.appbdcs.service.ICourseProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseProposalService implements ICourseProposalService {

    @Autowired
    private ICourseProposalRepository courseProposalRepository;

    @Override
    public void save(CourseProposal courseProposal) {
        courseProposalRepository.save(courseProposal);
    }

    @Override
    public Optional<CourseProposal> findCourseProposalById(Integer proposalId) {
        return courseProposalRepository.findByProposalId(proposalId);
    }
}
