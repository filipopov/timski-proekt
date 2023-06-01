package com.example.timskiproekt.domain.annotation;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomPhone implements ConstraintValidator<ValidPhone, String> {
    @Override
    public void initialize(ValidPhone validPhone) {
    }

    private static final Pattern patternPhone =
            Pattern.compile(
                    "^((00)?\\+?(389))?[\\/\\-\\s*\\.]?(((\\(0\\))|0)?\\s*7\\d{1})[\\/\\-\\s*\\.\\,]?([\\d]{3})[\\/\\-\\s*\\.\\,]?([\\d]{3})$");



    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNotBlank(phone) && patternPhone.matcher(phone).matches();
    }

}
