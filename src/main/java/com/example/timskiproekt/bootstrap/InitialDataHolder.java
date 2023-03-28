package com.example.timskiproekt.bootstrap;

import com.example.timskiproekt.domain.*;
import com.example.timskiproekt.domain.enumerations.Role;
import com.example.timskiproekt.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public record InitialDataHolder(AddressService addressService,
                                CategoryService categoryService,
                                CityService cityService,
                                ProductService productService,
                                UserService userService) {

    @PostConstruct
    public void init() {
        City bt = new City("Bitola");
        City sk = new City("Skopje");
        City be = new City("Berovo");

        cityService.saveAll(List.of(bt, sk, be));

        Address address = new Address("Partizanska");
        address.setCities(List.of(bt));

        addressService.save(address);

        Category category1 = new Category("Sok");
        Category category2 = new Category("Slatko");
        Category category3 = new Category("Tinkturi");

        categoryService.saveAll(List.of(category1, category2, category3));

        Product product1 = new Product("Sok od jagoda", BigDecimal.valueOf(300), 100, category1);
        Product product2 = new Product("Slatko od jagoda", BigDecimal.valueOf(200), 50, category2);
        Product product3 = new Product("Tinktura", BigDecimal.valueOf(400), 20, category3);

        productService.saveAll(List.of(product1, product2, product3));

        User user = new User("Filip", "Popov", "filip.popov13@gmail.com",
                "password", "123456789", Role.ADMIN, address);

        userService.save(user);
    }
}
