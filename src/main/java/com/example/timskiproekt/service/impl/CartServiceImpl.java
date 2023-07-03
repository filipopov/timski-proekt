package com.example.timskiproekt.service.impl;

import com.example.timskiproekt.domain.Cart;
import com.example.timskiproekt.domain.Product;
import com.example.timskiproekt.domain.User;
import com.example.timskiproekt.domain.enumerations.CartStatus;
import com.example.timskiproekt.domain.exceptions.CartNotFoundException;
import com.example.timskiproekt.domain.exceptions.ProductAlreadyInCartException;
import com.example.timskiproekt.domain.exceptions.ProductNotFoundException;
import com.example.timskiproekt.domain.exceptions.UserNotFoundException;
import com.example.timskiproekt.repository.CartRepository;
import com.example.timskiproekt.repository.ProductRepository;
import com.example.timskiproekt.repository.UserRepository;
import com.example.timskiproekt.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart save(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return this.cartRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Cart> findAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public List<Product> listAllProductsInCart(Long cartId) {
       if (!this.cartRepository.findById(cartId).isPresent())
           throw new CartNotFoundException();
       return this.cartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public Cart getActiveShoppingCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return this.cartRepository
                .findByUserAndStatus(user, CartStatus.CREATED)
                .orElseGet(() -> {
                    Cart cart = new Cart(user);
                    return this.cartRepository.save(cart);
                });
    }

    @Override
    public Cart addProductToShoppingCart(String username, Long productId) {
       Cart cart = getActiveShoppingCart(username);
       Product product = this.productRepository.findById(productId)
               .orElseThrow(ProductNotFoundException::new);
       if (cart.getProducts()
               .stream().filter(i -> i.getId().equals(productId))
               .collect(Collectors.toList()).size() > 0)
           throw new ProductAlreadyInCartException();
       cart.getProducts().add(product);
       return this.cartRepository.save(cart);
    }
}
