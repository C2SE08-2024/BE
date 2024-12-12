package com.example.appbdcs.service;

import com.example.appbdcs.model.Request;

import java.util.List;

public interface IRequestService {
    Request saveRequest(Request request);

    List<Request> getRequestsByBusinessId(Integer businessId);

    Request getRequestById(Integer requestId);
}
