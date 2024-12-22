package com.example.appbdcs.controller;

import com.example.appbdcs.dto.job.CreateCvRequest;
import com.example.appbdcs.dto.job.CvResponse;
import com.example.appbdcs.model.Cv;
import com.example.appbdcs.service.ICvService;
import com.example.appbdcs.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cvs")
@CrossOrigin(origins = "http://localhost:4200")
public class CvController {

    @Autowired
    private ICvService cvService;

    @Autowired
    private IStudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<?> createCv(@RequestBody CreateCvRequest request) {
        try {
            if (!studentService.existsById(request.getStudentId())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }

            Cv savedCv = cvService.createCv(
                    request.getStudentId(),
                    request.getName(),
                    request.getPosition(),
                    request.getSummary(),
                    request.getSkills(),
                    request.getEducation(),
                    request.getExperiences()
            );
            CvResponse response = mapToCvResponse(savedCv);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getMessage());
        }
    }

    public CvResponse mapToCvResponse(Cv cv) {
        CvResponse response = new CvResponse();
        response.setCvId(cv.getCvId());
        response.setStatus(cv.getStatus());
        response.setCreatedBy(cv.getCreatedBy());
        response.setCreatedDate(cv.getCreatedDate().toString());
        response.setStudentId(cv.getStudent().getStudentId());
        return response;
    }
}