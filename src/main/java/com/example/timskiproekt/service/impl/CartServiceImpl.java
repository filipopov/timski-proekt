package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.Cart;
import com.example.timskiproekt.repository.CartRepository;
import com.example.timskiproekt.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return this.cartRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Cart> findAll() {
        return this.cartRepository.findAll();
    }
}
