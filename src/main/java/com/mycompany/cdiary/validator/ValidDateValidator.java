package com.mycompany.cdiary.validator;

import com.mycompany.cdiary.util.CalendarUtil;
import java.util.Calendar;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String>  {

    @Override
    public void initialize(ValidDate constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Date date = CalendarUtil.parseDate(value);
        if (date == null) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return CalendarUtil.isValid(cal);
    }
    
}
