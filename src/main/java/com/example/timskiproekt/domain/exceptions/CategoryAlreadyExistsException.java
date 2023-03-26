package com.example.timskiproekt.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExistsException extends RuntimeException{

    public CategoryAlreadyExistsException() {
        super("Category already exists");
    }
    public CategoryAlreadyExistsException(String s) {
        super("New name cannot be the same with the old category name");
    }
}
