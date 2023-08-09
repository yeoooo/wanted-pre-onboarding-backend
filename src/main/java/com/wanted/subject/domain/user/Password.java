package com.wanted.subject.domain.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Constraint(validatedBy = isUpperLengthEight.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Password {
    String message() default "입력하신 비밀번호가 8자리를 넘지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
