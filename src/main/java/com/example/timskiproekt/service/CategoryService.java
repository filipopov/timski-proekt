package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<Category> saveAll(List<Category> categories);

    Category findById(Long id);

    List<Category> findAll();
}
