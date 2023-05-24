package com.example.timskiproekt.domain.exceptions;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException () {
        super("Invalid credentials");
    }
}
