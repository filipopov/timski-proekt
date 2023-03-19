package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.Product;
import com.example.timskiproekt.repository.ProductRepository;
import com.example.timskiproekt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return this.productRepository.saveAll(products);
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }
}
