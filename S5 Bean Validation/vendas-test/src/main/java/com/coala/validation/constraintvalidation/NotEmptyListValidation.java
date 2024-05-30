package com.coala.validation.constraintvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.coala.validation.NotEmptyList;

import java.util.List;

public class NotEmptyListValidation implements ConstraintValidator<NotEmptyList, List>{

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        
    }
    
}
