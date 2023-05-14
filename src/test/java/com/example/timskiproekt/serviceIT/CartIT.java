package com.example.timskiproekt.serviceIT;

import com.example.timskiproekt.domain.Cart;
import com.example.timskiproekt.repository.CartRepository;
import com.example.timskiproekt.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CartIT {

    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartServiceImpl(cartRepository);
    }

    @Test
    public void testSaveCart() {
        // Create a cart object
        Cart cart = new Cart();
        cart.setId(1L);

        // Set up mock behavior
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        // Call the service method
        Cart savedCart = cartService.save(cart);

        // Verify the result
        assertEquals(cart, savedCart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    public void testFindById() {
        // Create a cart object
        Cart cart = new Cart();
        cart.setId(1L);

        // Set up mock behavior
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        // Call the service method
        Cart foundCart = cartService.findById(1L);

        // Verify the result
        assertEquals(cart, foundCart);
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        // Create a list of cart objects
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart());
        carts.add(new Cart());

        // Set up mock behavior
        when(cartRepository.findAll()).thenReturn(carts);

        // Call the service method
        List<Cart> foundCarts = cartService.findAll();

        // Verify the result
        assertEquals(carts, foundCarts);
        verify(cartRepository, times(1)).findAll();
    }
}
