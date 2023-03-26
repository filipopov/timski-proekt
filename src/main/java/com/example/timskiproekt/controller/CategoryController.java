package com.example.timskiproekt.controller;

import com.example.timskiproekt.domain.Category;
import com.example.timskiproekt.domain.dto.AddressDto;
import com.example.timskiproekt.domain.dto.CategoryDto;
import com.example.timskiproekt.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Category> createCategory(@RequestParam String name) {
        return ResponseEntity.ok(this.categoryService.save(new Category(name)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id,
                                               @RequestParam CategoryDto categoryDto){
        categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteAll(){
        categoryService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
