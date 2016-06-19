package com.shuye.framework.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.shuye.framework.validation.validator.EqualLengthValidator;

import java.lang.annotation.*;

/**
 * 等于长度注解
 *
 * @author huangyong
 * @since 1.0.0
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualLengthValidator.class)
public @interface EqualLength {

    String message() default "equal_length";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}
