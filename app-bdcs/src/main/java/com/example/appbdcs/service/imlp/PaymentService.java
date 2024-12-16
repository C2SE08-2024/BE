package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Payment;
import com.example.appbdcs.repository.IPaymentRepository;
import com.example.appbdcs.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;


    @Override
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment findPaymentByTnxRef(String tnxRef) {
        return paymentRepository.findPaymentByTnxRef(tnxRef).orElse(null);
    }

    @Override
    public void deleteByTnxRef(String tnxRef) {
        Payment payment = paymentRepository.findPaymentByTnxRef(tnxRef).orElse(null);
        if (payment != null) {
            paymentRepository.deleteDetailsByPaymentId(payment.getId());
            paymentRepository.deleteById(payment.getId());
        }
    }

    @Override
    public Payment findPaymentByCartAndCourse(Integer cartId, Integer courseId) {
        return paymentRepository.findPaymentByCartAndCourse(cartId, courseId);
    }
}
