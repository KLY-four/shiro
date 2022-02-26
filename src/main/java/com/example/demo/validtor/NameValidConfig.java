package com.example.demo.validtor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;

public class NameValidConfig implements ConstraintValidator<NameValid,String> {
    int value;
    @Override
    public void initialize(NameValid constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String o, ConstraintValidatorContext context) {
        if(o != null){
            if(o.length() == value){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
}
