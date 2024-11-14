package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.business.BusinessInfo;
import com.example.appbdcs.model.Business;
import com.example.appbdcs.repository.IBusinessRepository;
import com.example.appbdcs.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BusinessService implements IBusinessService {

    @Autowired
    private IBusinessRepository businessRepository;

    @Override
    public void save(Business business) {
        businessRepository.save(business);
    }

    @Override
    public Page<Business> findAllBusiness(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Business> searchBusiness(String type, String name, String address, String phone, Pageable pageable) {
        return null;
    }

    @Override
    public Business businessLimit() {
        return businessRepository.limitBusiness();
    }

    @Override
    public void saveBusiness(BusinessInfo businessInfo) {

    }

    @Override
    public Business findById(Integer id) {
        return null;
    }

    @Override
    public void updateBusiness(BusinessInfo businessInfo, Integer id) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
