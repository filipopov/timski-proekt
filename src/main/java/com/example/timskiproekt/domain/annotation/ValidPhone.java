package com.example.timskiproekt.domain.annotation;


import jakarta.validation.Constraint;

import java.lang.annotation.*;


@Constraint(validatedBy = CustomPhone.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhone {

    String message()  default "Invalid phone";
}
