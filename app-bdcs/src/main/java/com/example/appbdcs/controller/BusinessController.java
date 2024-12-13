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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/business")
@CrossOrigin(origins = "http://localhost:4200")
public class    BusinessController {
    @Autowired
    private IBusinessService businessService;

    @Autowired
    private IStudentService studentService;


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

    @PostMapping("/create")
    public ResponseEntity<BusinessDTO> createBusiness(@RequestBody BusinessDTO businessDTO) {
        try {
            // Gọi service để tạo doanh nghiệp mới
            BusinessDTO createdBusiness = businessService.createBusiness(businessDTO);
            return new ResponseEntity<>(createdBusiness, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            // Nếu có lỗi như trùng businessCode
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // API để lấy thông tin doanh nghiệp theo ID
    @GetMapping("/{id}")
    public ResponseEntity<BusinessDTO> getBusinessById(@PathVariable Integer id) {
        Optional<BusinessDTO> businessDTO = businessService.getBusinessById(id);
        return businessDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // API để lấy danh sách tất cả doanh nghiệp
    @GetMapping
    public ResponseEntity<List<BusinessDTO>> getAllBusinesses() {
        List<BusinessDTO> businesses = businessService.getAllBusinesses();
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    // API để tìm kiếm doanh nghiệp theo tên
    @GetMapping("/search")
    public ResponseEntity<List<BusinessDTO>> searchBusinessesByName(@RequestParam String name) {
        List<BusinessDTO> businesses = businessService.searchBusinessesByName(name);
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    // API để cập nhật thông tin doanh nghiệp
    @PutMapping("/{id}")
    public ResponseEntity<BusinessDTO> updateBusiness(@PathVariable Integer id, @RequestBody BusinessDTO businessDTO) {
        Optional<BusinessDTO> updatedBusiness = businessService.updateBusiness(id, businessDTO);
        return updatedBusiness.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // API để xóa doanh nghiệp theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Integer id) {
        boolean isDeleted = businessService.deleteBusiness(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
