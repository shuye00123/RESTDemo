package com.shuye.framework.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.shuye.framework.validation.MaxLength;

/**
 * 最大长度注解验证器
 *
 * @author huangyong
 * @since 1.0.0
 */
public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {

    private int length;

    @Override
    public void initialize(MaxLength constraintAnnotation) {
        length = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() <= length;
    }
}
