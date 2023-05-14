package com.example.timskiproekt.controller;

import com.example.timskiproekt.domain.Category;
import com.example.timskiproekt.domain.Product;
import com.example.timskiproekt.domain.dto.ProductDto;
import com.example.timskiproekt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestParam String name,
                                                 @RequestParam BigDecimal price,
                                                 @RequestParam Integer quantity,
                                                 @RequestParam Category category) {
        return ResponseEntity.ok(this.productService.save(new Product(name, price, quantity, category)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id,
                                              @RequestParam ProductDto productDto){
        productService.updateProduct(id, productDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteAll(){
        productService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
