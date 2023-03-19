package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.Cart;

import java.util.List;

public interface CartService {

    Cart save(Cart cart);

    Cart findById(Long id);

    List<Cart> findAll();
}
