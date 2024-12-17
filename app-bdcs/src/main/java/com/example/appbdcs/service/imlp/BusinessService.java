package com.example.appbdcs.service.imlp;

import com.example.appbdcs.dto.business.BusinessDTO;
import com.example.appbdcs.dto.business.BusinessUserDetailDto;
import com.example.appbdcs.model.Business;
import com.example.appbdcs.repository.IBusinessRepository;
import com.example.appbdcs.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusinessService implements IBusinessService {

    @Autowired
    private IBusinessRepository businessRepository;

    @Override
    public List<BusinessDTO> getAllBusinesses() {
        List<Business> businesses = businessRepository.findAll();
        return businesses.stream()
                .map(this::convertToBusinessDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BusinessDTO findBusinessById(Integer businessId) {
        Optional<Business> business = businessRepository.findBusinessById(businessId);
        return business.map(this::convertToBusinessDTO).orElse(null);
    }

    @Override
    public void save(Business business) {
        businessRepository.save(business);
    }

    @Override
    public Business businessLimit() {
        return businessRepository.limitBusiness();
    }

    @Override
    public BusinessUserDetailDto findUserDetailByUsername(String username) {
        Tuple tuple = businessRepository.findUserDetailByUsername(username).orElse(null);

        if (tuple != null) {
            return BusinessUserDetailDto.TupleToBusinessDto(tuple);
        }

        return null;
    }

    @Override
    public Optional<Business> findBusinessByUsername(String username) {
        return businessRepository.findByUsername(username);
    }

    @Override
    public Business findById(Integer businessId) {
        return businessRepository.findById(businessId).orElse(null);
    }

    private BusinessDTO convertToBusinessDTO(Business business) {
        return new BusinessDTO(
                business.getBusinessId(),
                business.getBusinessCode(),
                business.getBusinessName(),
                business.getBusinessEmail(),
                business.getBusinessPhone(),
                business.getBusinessAddress(),
                business.getBusinessImg(),
                business.getDescription(),
                business.getIsEnable(),
                business.getIndustry(),
                business.getFoundedYear(),
                business.getWebsite(),
                business.getSize()
        );
    }
}
