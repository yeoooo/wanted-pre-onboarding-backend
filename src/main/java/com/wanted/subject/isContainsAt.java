package com.wanted.subject;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class isContainsAt implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.contains("@");
    }
}
