package com.example.timskiproekt.bootstrap;

import com.example.timskiproekt.service.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataHolder {

    private final AddressService addressService;
    private final CartService cartService;
    private final CategoryService categoryService;
    private final CityService cityService;
    private final ProductService productService;
    private final UserService userService;

    @PostConstruct
    public void init() {
        // populate data here
    }
}
