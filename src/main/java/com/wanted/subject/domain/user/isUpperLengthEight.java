package com.wanted.subject.domain.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class isUpperLengthEight implements ConstraintValidator<Password, String> {
    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() >= 8;
    }
}
