package com.example.appbdcs.service;

import com.example.appbdcs.dto.business.BusinessDTO;
import com.example.appbdcs.dto.business.BusinessUserDetailDto;
import com.example.appbdcs.model.Business;

import java.util.List;
import java.util.Optional;

public interface IBusinessService {
    List<BusinessDTO> getAllBusinesses();

    BusinessDTO findBusinessById(Integer businessId);

    void save(Business business);

    Business businessLimit();

    BusinessUserDetailDto findUserDetailByUsername(String username);

    Optional<Business> findBusinessByUsername(String username);
}
