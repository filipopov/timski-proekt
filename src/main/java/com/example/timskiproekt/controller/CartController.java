package com.example.timskiproekt.controller;

import com.example.timskiproekt.domain.Cart;
import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCartPage(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        cartService.getActiveShoppingCart(username);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-product/{id}")
    public ResponseEntity<Cart> addProductToCart(Authentication authentication, @PathVariable Long id) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        cartService.addProductToShoppingCart(username, id);

        return ResponseEntity.ok().build();
    }
}
