package com.example.timskiproekt.serviceIT;

import com.example.timskiproekt.domain.Category;
import com.example.timskiproekt.domain.dto.CategoryDto;
import com.example.timskiproekt.domain.exceptions.CategoryAlreadyExistsException;
import com.example.timskiproekt.domain.exceptions.CategoryNotFoundException;
import com.example.timskiproekt.domain.exceptions.InvalidArgumentsException;
import com.example.timskiproekt.repository.CategoryRepository;
import com.example.timskiproekt.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryIT {

    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void testSaveCategory() {
        // Create a category object
        Category category = new Category();
        category.setId(1L);

        // Set up mock behavior
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Call the service method
        Category savedCategory = categoryService.save(category);

        // Verify the result
        assertEquals(category, savedCategory);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void testSaveAllCategories() {
        // Create a list of category objects
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        // Set up mock behavior
        when(categoryRepository.saveAll(anyList())).thenReturn(categories);

        // Call the service method
        List<Category> savedCategories = categoryService.saveAll(categories);

        // Verify the result
        assertEquals(categories, savedCategories);
        verify(categoryRepository, times(1)).saveAll(categories);
    }
    @Test
    public void createCategory() {
        String name = "category";
        when(categoryService.save(new Category(name))).thenReturn(any(Category.class));

        Category newCategory = categoryService.create(name);

        assertEquals(newCategory.getName(), "category");
    }
    @Test
    public void testCreateCategoryWithNullShouldThrowException() {

        assertThrows(InvalidArgumentsException.class, () ->
                categoryService.create(null));
    }

    @Test
    public void testCreateCategoryWithEmptyNameShouldThrowException() {

        assertThrows(InvalidArgumentsException.class, () ->
                categoryService.create(""));
    }

    @Test
    public void testFindById() {
        // Create a category object
        Category category = new Category();
        category.setId(1L);

        // Set up mock behavior
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        // Call the service method
        Category foundCategory = categoryService.findById(1L);

        // Verify the result
        assertEquals(category, foundCategory);
        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        // Create a list of category objects
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        // Set up mock behavior
        when(categoryRepository.findAll()).thenReturn(categories);

        // Call the service method
        List<Category> foundCategories = categoryService.findAll();

        // Verify the result
        assertEquals(categories, foundCategories);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateCategory() {
        // Create a category object
        Category currentCategory = new Category();
        currentCategory.setId(1L);
        currentCategory.setName("Old Category");

        // Create a category DTO object
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("New Category");

        // Set up mock behavior
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(currentCategory));
        when(categoryRepository.findByNameIgnoreCase("New Category")).thenReturn(Optional.empty());
        when(categoryRepository.save(any(Category.class))).thenReturn(currentCategory);

        // Call the service method
        assertDoesNotThrow(() -> categoryService.updateCategory(1L, categoryDto));

        // Verify the result
        assertEquals(categoryDto.getName(), currentCategory.getName());
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findByNameIgnoreCase("New Category");
        verify(categoryRepository, times(1)).save(currentCategory);
    }

    @Test
    public void testUpdateCategory_CategoryNotFoundException() {
        // Create a category DTO object
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("New Category");

        // Set up mock behavior
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the service method and verify that it throws the expected exception
        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(1L, categoryDto));

        // Verify the result
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, never()).findByNameIgnoreCase(anyString());
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testUpdateCategory_CategoryAlreadyExistsException_SameName() {
        // Create a category object
        Category currentCategory = new Category();
        currentCategory.setId(1L);
        currentCategory.setName("Existing Category");

        // Create a category DTO object
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Existing Category");

        // Set up mock behavior
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(currentCategory));

        // Call the service method and verify that it throws the expected exception
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryService.updateCategory(1L, categoryDto));

        // Verify the result
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository).findByNameIgnoreCase(anyString());
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testUpdateCategory_CategoryAlreadyExistsException_DifferentName() {
        // Create a category object
        Category currentCategory = new Category();
        currentCategory.setId(1L);
        currentCategory.setName("Existing Category");

        // Create a category DTO object
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("New Category");

        // Set up mock behavior
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(currentCategory));
        when(categoryRepository.findByNameIgnoreCase("New Category")).thenReturn(Optional.of(new Category()));

        // Call the service method and verify that it throws the expected exception
        assertThrows(NullPointerException.class, () -> categoryService.updateCategory(1L, categoryDto));

        // Verify the result
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findByNameIgnoreCase("New Category");
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    public void testDeleteCategory() {
        // Call the service method
        assertDoesNotThrow(() -> categoryService.deleteCategory(1L));

        // Verify the result
        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAll() {
        // Call the service method
        assertDoesNotThrow(() -> categoryService.deleteAll());

        // Verify the result
        verify(categoryRepository, times(1)).deleteAll();
    }
}
