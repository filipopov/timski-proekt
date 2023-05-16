package com.example.timskiproekt.serviceIT;

import com.example.timskiproekt.domain.Category;
import com.example.timskiproekt.domain.Product;
import com.example.timskiproekt.domain.dto.ProductDto;
import com.example.timskiproekt.domain.exceptions.CategoryNotFoundException;
import com.example.timskiproekt.domain.exceptions.InvalidPriceException;
import com.example.timskiproekt.domain.exceptions.InvalidQuantityException;
import com.example.timskiproekt.repository.CategoryRepository;
import com.example.timskiproekt.repository.ProductRepository;
import com.example.timskiproekt.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductIT {

    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository, categoryRepository);
    }

    @Test
    void saveProduct_ReturnsSavedProduct() {
        // Arrange
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product savedProduct = productService.save(product);

        // Assert
        assertNotNull(savedProduct);
        assertSame(product, savedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void saveAllProducts_ReturnsSavedProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        when(productRepository.saveAll(products)).thenReturn(products);

        // Act
        List<Product> savedProducts = productService.saveAll(products);

        // Assert
        assertNotNull(savedProducts);
        assertEquals(products.size(), savedProducts.size());
        assertSame(products.get(0), savedProducts.get(0));
        assertSame(products.get(1), savedProducts.get(1));
        verify(productRepository, times(1)).saveAll(products);
    }

    @Test
    void findById_ExistingId_ReturnsProduct() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        Product foundProduct = productService.findById(id);

        // Assert
        assertNotNull(foundProduct);
        assertSame(product, foundProduct);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void findById_NonExistingId_ThrowsProductNotFoundException() {
        // Arrange
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.findById(id));
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void findAll_ReturnsAllProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> foundProducts = productService.findAll();

        // Assert
        assertNotNull(foundProducts);
        assertEquals(products.size(), foundProducts.size());
        assertSame(products.get(0), foundProducts.get(0));
        assertSame(products.get(1), foundProducts.get(1));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void updateProduct_WithInvalidPrice_ThrowsInvalidPriceException() {
        // Arrange
        Long id = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(id);
        existingProduct.setName("Old Name");
        existingProduct.setPrice(BigDecimal.valueOf(10));
        existingProduct.setQuantity(5);
        existingProduct.setCategory(new Category());

        ProductDto productDto = new ProductDto();
        productDto.setName("New Name");
        productDto.setPrice(BigDecimal.valueOf(-5)); // Invalid price
        productDto.setQuantity(10);
        productDto.setCategory(new Category());

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(InvalidPriceException.class, () -> productService.updateProduct(id, productDto));
        verify(productRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).findAll();
        verify(productRepository, times(0)).save(existingProduct); // Should not be called
    }

    @Test
    void updateProduct_WithInvalidQuantity_ThrowsInvalidQuantityException() {
        // Arrange
        Long id = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(id);
        existingProduct.setName("Old Name");
        existingProduct.setPrice(BigDecimal.valueOf(10));
        existingProduct.setQuantity(5);
        existingProduct.setCategory(new Category());

        ProductDto productDto = new ProductDto();
        productDto.setName("New Name");
        productDto.setPrice(BigDecimal.valueOf(20));
        productDto.setQuantity(-5); // Invalid quantity
        productDto.setCategory(new Category());

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(InvalidQuantityException.class, () -> productService.updateProduct(id, productDto));
        verify(productRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).findAll();
        verify(productRepository, times(0)).save(existingProduct); // Should not be called
    }

    @Test
    void updateProduct_WithInvalidCategory_ThrowsCategoryNotFoundException() {
        // Arrange
        Long id = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(id);
        existingProduct.setName("Old Name");
        existingProduct.setPrice(BigDecimal.valueOf(10));
        existingProduct.setQuantity(5);
        existingProduct.setCategory(new Category());

        ProductDto productDto = new ProductDto();
        productDto.setName("New Name");
        productDto.setPrice(BigDecimal.valueOf(20));
        productDto.setQuantity(10);
        productDto.setCategory(new Category());
        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> productService.updateProduct(id, productDto));
        verify(productRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).findAll();
        verify(productRepository, times(0)).save(existingProduct); // Should not be called
    }

    @Test
    void deleteProduct_ValidId_ProductIsDeleted() {
        // Arrange
        Long id = 1L;

        // Act
        productService.deleteProduct(id);

        // Assert
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteAll_AllProductsAreDeleted() {
        // Act
        productService.deleteAll();

        // Assert
        verify(productRepository, times(1)).deleteAll();
    }
}
