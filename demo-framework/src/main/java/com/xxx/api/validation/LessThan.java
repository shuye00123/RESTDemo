package com.xxx.api.validation;

import com.xxx.api.validation.validator.LessThanValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 小于值注解
 *
 * @author huangyong
 * @since 1.0.0
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LessThanValidator.class)
public @interface LessThan {

    String message() default "less_than";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}
