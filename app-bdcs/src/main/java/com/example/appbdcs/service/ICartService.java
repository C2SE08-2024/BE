package com.example.appbdcs.service;

import com.example.appbdcs.model.Cart;

public interface ICartService {
    Cart update(Cart cart);

    Cart findByUsername(String username);

    Cart findById(Integer id);
}
