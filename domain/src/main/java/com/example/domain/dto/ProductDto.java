package com.example.domain.dto;

import com.example.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    String name;

    BigDecimal price;

    Integer quantity;

    Category category;


}
