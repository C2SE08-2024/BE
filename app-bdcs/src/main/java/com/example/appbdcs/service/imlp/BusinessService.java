package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Business;
import com.example.appbdcs.repository.IBusinessRepository;
import com.example.appbdcs.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Business businessLimit() {
        return businessRepository.limitBusiness();
    }
}
