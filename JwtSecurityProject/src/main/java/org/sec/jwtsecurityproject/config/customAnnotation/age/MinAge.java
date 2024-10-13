package org.sec.jwtsecurityproject.config.customAnnotation.age;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MinAgeValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinAge {
    String message() default "You must be at least {value} years old";
    int value();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
