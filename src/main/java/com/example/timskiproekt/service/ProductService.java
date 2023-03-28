package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.Product;
import com.example.timskiproekt.domain.dto.ProductDto;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> saveAll(List<Product> products);

    Product findById(Long id);

    List<Product> findAll();

    void updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);

    void deleteAll();
}
