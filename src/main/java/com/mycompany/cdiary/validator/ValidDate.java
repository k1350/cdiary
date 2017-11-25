package com.mycompany.cdiary.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateValidator.class)
public @interface ValidDate {

    String message() default "{com.mycompany.cdiary.validator.ValidDate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
