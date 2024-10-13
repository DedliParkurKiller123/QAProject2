package org.sec.jwtsecurityproject.config.customAnnotation.age;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class MinAgeValidator implements ConstraintValidator<MinAge, LocalDate> {
    private int minAge;
    @Override
    public void initialize(MinAge constraintAnnotation) {
        this.minAge = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate DOB, ConstraintValidatorContext constraintValidatorContext) {
        return DOB != null && Period.between(DOB, LocalDate.now()).getYears() >= minAge;
    }
}
