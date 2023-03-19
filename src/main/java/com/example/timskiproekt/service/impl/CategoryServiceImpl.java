package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.Category;
import com.example.timskiproekt.repository.CategoryRepository;
import com.example.timskiproekt.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> saveAll(List<Category> categories) {
        return this.categoryRepository.saveAll(categories);
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }
}
