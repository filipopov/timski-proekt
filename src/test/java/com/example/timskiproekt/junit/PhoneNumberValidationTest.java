package com.example.timskiproekt.junit;

import com.example.timskiproekt.domain.annotation.CustomPhone;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PhoneNumberValidationTest {

    private ConstraintValidatorContext cxt;

    @ParameterizedTest
    @ValueSource(strings = {"075 123 123", "+389 75 123 123", "077-123-123"})
    void isCustomDomainValid(String input){
        assertEquals(true, new CustomPhone().isValid(input, cxt));
    }

    @ParameterizedTest
    @ValueSource(strings = {"02 1123 123", "080/123 123", "07/123456"})
    void isCustomDomainInValid(String input){
        assertEquals(false, new CustomPhone().isValid(input, cxt));
    }
}
