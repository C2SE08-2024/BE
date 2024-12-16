package com.example.appbdcs.controller;

import com.example.appbdcs.dto.business.BusinessDTO;
import com.example.appbdcs.dto.business.BusinessUserDetailDto;
import com.example.appbdcs.dto.business.CourseProposalDTO;
import com.example.appbdcs.dto.instructor.InstructorProposalDTO;
import com.example.appbdcs.model.Business;
import com.example.appbdcs.model.CourseProposal;
import com.example.appbdcs.model.Instructor;
import com.example.appbdcs.service.IBusinessService;
import com.example.appbdcs.service.ICourseProposalService;
import com.example.appbdcs.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/business")
@CrossOrigin(origins = "http://localhost:4200")
public class BusinessController {

    @Autowired
    private IBusinessService businessService;

    @Autowired
    private ICourseProposalService courseProposalService;

    @Autowired
    private IInstructorService instructorService;

    @GetMapping("")
    public ResponseEntity<List<BusinessDTO>> getAllBusiness() {
        List<BusinessDTO> businessList = businessService.getAllBusinesses();
        if (businessList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(businessList, HttpStatus.OK);
    }

    @GetMapping("/{businessId}")
    public BusinessDTO getBusinessById(@PathVariable Integer businessId) {
        BusinessDTO businessDTO = businessService.findBusinessById(businessId);
        if (businessDTO == null) {
            throw new RuntimeException("The business does not exist!");
        }
        return businessDTO;
    }

    @PostMapping("/managers/course-proposals")
    public ResponseEntity<?> createCourseProposal(@RequestBody CourseProposalDTO courseProposalDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in to submit a course proposal.");
        }

        String username = authentication.getName();

        Optional<Business> businessOptional = businessService.findBusinessByUsername(username);
        if (!businessOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Business not found.");
        }

        CourseProposal proposal = new CourseProposal();
        proposal.setCourseName(courseProposalDTO.getCourseName());
        proposal.setDescription(courseProposalDTO.getDescription());
        proposal.setBusiness(businessOptional.get());
        proposal.setInstructor(null);

        courseProposalService.save(proposal);

        return ResponseEntity.status(HttpStatus.CREATED).body("Course proposal submitted successfully.");
    }

    @PostMapping("/admin/course-proposals/{proposalId}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> approveCourseProposal(@PathVariable Integer proposalId,
                                                   @RequestBody InstructorProposalDTO instructorProposalDTO) {
        Optional<CourseProposal> courseProposalOptional = courseProposalService.findCourseProposalById(proposalId);
        if (!courseProposalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course proposal not found.");
        }
        CourseProposal courseProposal = courseProposalOptional.get();
        if (courseProposal.getIsApproved()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course proposal has already been approved.");
        }
        courseProposal.setIsApproved(true);
        Optional<Instructor> instructorOptional = instructorService.findInstructorById(instructorProposalDTO.getInstructorId());
        if (!instructorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instructor not found.");
        }
        courseProposal.setInstructor(instructorOptional.get());

        courseProposalService.save(courseProposal);

        return ResponseEntity.status(HttpStatus.OK).body("Course proposal approved and instructor assigned successfully.");
    }

    @GetMapping("/managers/detail")
    public ResponseEntity<BusinessUserDetailDto> getDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        BusinessUserDetailDto businessUserDetailDto = businessService.findUserDetailByUsername(username);

        if (businessUserDetailDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(businessUserDetailDto, HttpStatus.OK);
    }
}