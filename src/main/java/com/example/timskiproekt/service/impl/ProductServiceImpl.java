package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.Category;
import com.example.timskiproekt.domain.Product;
import com.example.timskiproekt.domain.dto.ProductDto;
import com.example.timskiproekt.domain.exceptions.CategoryNotFoundException;
import com.example.timskiproekt.domain.exceptions.InvalidPriceException;
import com.example.timskiproekt.domain.exceptions.InvalidQuantityException;
import com.example.timskiproekt.domain.exceptions.ProductNotFoundException;
import com.example.timskiproekt.repository.CategoryRepository;
import com.example.timskiproekt.repository.ProductRepository;
import com.example.timskiproekt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

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

    @Override
    public void updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        List<Category> categories = categoryRepository.findAll();
        if ((productDto.getPrice().compareTo(BigDecimal.valueOf(0))) == 1)
            throw new InvalidPriceException();
        if (productDto.getQuantity() < 0)
            throw new InvalidQuantityException();
        if (!categories.contains(productDto.getCategory()))
            throw new CategoryNotFoundException();

        product.setName(product.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(product.getQuantity());
        product.setCategory(productDto.getCategory());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
