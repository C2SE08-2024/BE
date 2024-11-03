package com.example.appbdcs.service.imlp;






import com.example.appbdcs.dto.business.BusinessInfo;
import com.example.appbdcs.model.Business;
import com.example.appbdcs.model.Instructor;
import com.example.appbdcs.repository.IBusinessRepository;
import com.example.appbdcs.repository.IInstructorRepository;
import com.example.appbdcs.service.IBusinessService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;
@Service
public class BusinessService implements IBusinessService {
    private final IBusinessRepository businessRepository;

    @Autowired
    public BusinessService(IBusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

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
        return null;
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
