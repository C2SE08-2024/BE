package com.example.appbdcs.service.imlp;

import com.example.appbdcs.model.Cart;
import com.example.appbdcs.repository.ICartRepository;
import com.example.appbdcs.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Cart update(Cart cart) {
        Integer id = cart.getCartId();
        String name = cart.getReceiverName();
        String address = cart.getReceiverAddress();
        String phone = cart.getReceiverPhone();
        String email = cart.getReceiverEmail();
        cartRepository.updateCart(id, name, address, phone, email);
        return cart;
    }

    @Override
    public Cart findByUsername(String username) {
        return cartRepository.findCartByUsername(username).orElse(null);
    }

    @Override
    public Cart findById(Integer id) {
        return cartRepository.findById(id).orElse(null);
    }
}
