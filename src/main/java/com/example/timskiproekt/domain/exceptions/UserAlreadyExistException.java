package com.example.timskiproekt.domain.exceptions;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException() {
        super("User already exist.");
    }
}
