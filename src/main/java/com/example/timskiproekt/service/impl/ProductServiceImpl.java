package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.repository.ProductRepository;
import com.example.timskiproekt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
}
