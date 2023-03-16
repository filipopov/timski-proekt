package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.repository.CategoryRepository;
import com.example.timskiproekt.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
}
