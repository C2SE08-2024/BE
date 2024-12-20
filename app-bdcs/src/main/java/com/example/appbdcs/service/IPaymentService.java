package com.example.appbdcs.service;

import com.example.appbdcs.model.Payment;

public interface IPaymentService {
    Payment update(Payment payment);

    Payment findPaymentByTnxRef(String tnxRef);

    void deleteByTnxRef(String tnxRef);

    Payment findPaymentByCartAndCourse(Integer cartId, Integer courseId);
}
