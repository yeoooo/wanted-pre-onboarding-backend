package com.wanted.subject.domain.user;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Constraint(validatedBy = isContainsAt.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Email {
    String message() default "입력하신 이메일에 \'@\' 가 포함되어 있지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}