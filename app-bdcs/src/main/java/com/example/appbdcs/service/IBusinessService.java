package com.example.appbdcs.service;

import com.example.appbdcs.model.Business;

public interface IBusinessService {
    void save(Business business);

    Business businessLimit();
}
