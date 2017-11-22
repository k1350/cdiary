package com.mycompany.cdiary.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<CustomEmail, String> {

    @Override
    public void initialize(CustomEmail constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        // すべての全角スペースを削除
        String val = value.replaceAll("[　]*", "");
        // すべての空白文字を削除
        val = val.replaceAll("[\\s]*", "");
        if (val.contains("@") && val.length() > 2) {
            return true;
        }
        return false;
    }
    
}
