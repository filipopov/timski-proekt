package com.example.timskiproekt.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPriceException extends RuntimeException{

    public InvalidPriceException() {
        super("Price can not be smaller than zero");
    }
}
