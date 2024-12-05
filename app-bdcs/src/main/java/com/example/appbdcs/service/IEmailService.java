package com.example.appbdcs.service;

import com.example.appbdcs.model.Cart;
import com.example.appbdcs.model.CartDetail;

import java.util.List;

public interface IEmailService {
    void emailProcess(Cart cart, Integer totalAmount, List<CartDetail> details);
}
