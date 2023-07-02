package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.Category;
import com.example.timskiproekt.domain.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    Category save(Category category);
    Category create(String name);

    List<Category> saveAll(List<Category> categories);

    Category findById(Long id);

    List<Category> findAll();

    void updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(Long id);

    void deleteAll();

    List<Category> searchCategories(String searchText);
}
