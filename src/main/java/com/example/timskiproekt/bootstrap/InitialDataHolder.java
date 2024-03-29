package com.example.timskiproekt.bootstrap;

import com.example.timskiproekt.domain.*;
import com.example.timskiproekt.domain.enumerations.Role;
import com.example.timskiproekt.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public record InitialDataHolder(CategoryService categoryService,
                                ProductService productService,
                                UserService userService,
                                PasswordEncoder passwordEncoder) {

    @PostConstruct
    public void init() {

        Category category1 = new Category("Sok");
        Category category2 = new Category("Slatko");
        Category category3 = new Category("Tinkturi");

        categoryService.saveAll(List.of(category1, category2, category3));

        Product product1 = new Product("Sok od jagoda", BigDecimal.valueOf(300), 100, category1);
        Product product2 = new Product("Slatko od jagoda", BigDecimal.valueOf(200), 50, category2);
        Product product3 = new Product("Tinktura", BigDecimal.valueOf(400), 20, category3);

        productService.saveAll(List.of(product1, product2, product3));

        User user1 = new User("Filip", "Popov", "fpopov@gmail.com",
                "fpopov", passwordEncoder.encode("pass"), "075 123 123", Role.ADMIN, "address1");

        User user2 = new User("Natalija", "Chitinska", "ncitinska@gmail.com","nc",
                passwordEncoder.encode("pass"), "075 123 123", "address1");

        userService.saveAll(List.of(user1, user2));
    }
}
