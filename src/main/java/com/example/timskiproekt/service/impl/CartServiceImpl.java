package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.repository.CartRepository;
import com.example.timskiproekt.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
}
