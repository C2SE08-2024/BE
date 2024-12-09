package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Request;
import com.example.appbdcs.repository.IRequestRepository;
import com.example.appbdcs.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService implements IRequestService {

    @Autowired
    private IRequestRepository requestRepository;


    @Override
    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }
    public List<Request> getRequestsByBusinessId(Integer businessId) {
        return requestRepository.findByBusiness_BusinessId(businessId);
    }
    public Request getRequestById(Integer requestId) {
        return requestRepository.findById(requestId).orElse(null);
    }
}
