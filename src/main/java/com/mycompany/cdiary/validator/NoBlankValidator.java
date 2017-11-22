package com.mycompany.cdiary.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoBlankValidator implements ConstraintValidator<NoBlank, String> {

    @Override
    public void initialize(NoBlank constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        // すべての全角スペースを置換
        String val = value.replaceAll("[　]*", "");
        // すべての空白文字を置換
        val = val.replaceAll("[\\s]*", "");
        if (val.isEmpty()) {
            return false;
        }
        return true;
    }
    
}
