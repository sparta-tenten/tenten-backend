package com.sparta.tentenbackend.global.annotation;

import com.sparta.tentenbackend.global.validator.UUIDValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {UUIDValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUUID {

    String message() default "유효하지 않은 UUID 입니다!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
