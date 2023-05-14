package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.Category;
import com.example.timskiproekt.domain.dto.CategoryDto;
import com.example.timskiproekt.domain.exceptions.CategoryAlreadyExistsException;
import com.example.timskiproekt.domain.exceptions.CategoryNotFoundException;
import com.example.timskiproekt.repository.CategoryRepository;
import com.example.timskiproekt.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void updateCategory(Long id, CategoryDto categoryDto) {
        Category currentCategory =
                categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        Optional<Category> category = categoryRepository.findByNameIgnoreCase(categoryDto.getName());

        if (currentCategory.getName().equals(categoryDto.getName()))
            throw new CategoryAlreadyExistsException();
        if(category.isPresent() && !category.get().getId().equals(id))
            throw new CategoryAlreadyExistsException("");

        currentCategory.setName(categoryDto.getName());
        categoryRepository.save(currentCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
