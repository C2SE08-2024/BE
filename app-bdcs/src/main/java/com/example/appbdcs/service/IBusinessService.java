package com.example.appbdcs.service;

import com.example.appbdcs.dto.business.BusinessInfo;
import com.example.appbdcs.model.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBusinessService {

    void save(Business business);

    Page<Business> findAllBusiness(Pageable pageable);

    Page<Business> searchBusiness(String type, String name, String address, String phone, Pageable pageable);

    Business businessLimit();

    void saveBusiness(BusinessInfo businessInfo);

    Business findById(Integer id);

    void updateBusiness(BusinessInfo businessInfo, Integer id);

    void deleteById(Integer id);
}
