package com.example.timskiproekt.domain.exceptions;


public class ProductAlreadyInCartException extends RuntimeException{

    public ProductAlreadyInCartException() {
        super("Product is already in the cart");
    }
}
