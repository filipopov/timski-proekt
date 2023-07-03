package com.example.timskiproekt.service;

import com.example.timskiproekt.domain.Cart;
import com.example.timskiproekt.domain.Product;

import java.util.List;

public interface CartService {

    Cart save(Cart cart);

    Cart findById(Long id);

    List<Cart> findAll();
    List<Product> listAllProductsInCart(Long cartId);

    Cart getActiveShoppingCart(String username);

    Cart addProductToShoppingCart(String username, Long productId);

}
