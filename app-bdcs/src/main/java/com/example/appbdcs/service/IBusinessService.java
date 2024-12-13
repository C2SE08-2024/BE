package com.example.appbdcs.service;

import com.example.appbdcs.dto.business.BusinessDTO;
import com.example.appbdcs.dto.course.CourseDTO;
import com.example.appbdcs.model.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBusinessService {
    void save(Business business);

    Business businessLimit();

    Business findById(Integer businessId);


    // Lấy danh sách doanh nghiệp
    List<BusinessDTO> getAllBusinesses();

    // Lấy chi tiết doanh nghiệp theo businessCode
    BusinessDTO getBusinessByCode(String businessCode);

    BusinessDTO findBusinessById(Integer businessId);

    List<CourseDTO> getAllCourses();

    CourseDTO createCourse(CourseDTO courseDTO, Integer businessId);

    Optional<BusinessDTO> getBusinessById(Integer id);

    BusinessDTO createBusiness(BusinessDTO businessDTO);

    Optional<BusinessDTO> updateBusiness(Integer id, BusinessDTO businessDTO);

    boolean deleteBusiness(Integer id);

    List<BusinessDTO> searchBusinessesByName(String name);

    Page<BusinessDTO> getBusinessesPaginated(Pageable pageable);

}
