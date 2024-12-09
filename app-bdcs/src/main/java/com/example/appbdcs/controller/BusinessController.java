package com.example.appbdcs.controller;


import com.example.appbdcs.dto.business.BusinessDTO;
import com.example.appbdcs.service.IBusinessService;
import com.example.appbdcs.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.appbdcs.dto.course.CourseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/business")
@CrossOrigin(origins = "http://localhost:4200")
public class    BusinessController {
    @Autowired
    private IBusinessService businessService;

    @Autowired
    private IStudentService studentService;


    // Lấy danh sách doanh nghiệp
    @GetMapping("")
    public List<BusinessDTO> getAllBusinesses() {
        return businessService.getAllBusinesses();
    }

    // Lấy chi tiết doanh nghiệp theo businessCode
    @GetMapping("/code/{businessCode}")
    public BusinessDTO getBusinessByCode(@PathVariable String businessCode) {
        return businessService.getBusinessByCode(businessCode);
    }

    // Lấy chi tiết doanh nghiệp theo businessId
    @GetMapping("/id/{businessId}")
    public BusinessDTO findById(@PathVariable Integer businessId) {
        BusinessDTO businessDTO = businessService.findBusinessById(businessId);
        if (businessDTO == null) {
            throw new RuntimeException("Doanh nghiệp không tồn tại!");
        }
        return businessDTO;
    }


    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = businessService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Endpoint để doanh nghiệp tạo một khóa học
    @PostMapping("/create-course/{businessId}")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO, @PathVariable Integer businessId) {
        CourseDTO createdCourse = businessService.createCourse(courseDTO, businessId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }
}
