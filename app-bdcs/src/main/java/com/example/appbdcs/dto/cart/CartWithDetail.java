package com.example.appbdcs.dto.cart;

import com.example.appbdcs.model.Cart;
import com.example.appbdcs.model.CartDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartWithDetail {
    private Cart cart;
    private List<CartDetail> cartDetailList;
}
