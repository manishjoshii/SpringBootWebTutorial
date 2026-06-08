package com.manishjoshi.SpringBootWebTutorial.annotations.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        if (inputRole == null) return false;
        List<String> roles = Arrays.asList("USER", "ADMIN");
        return roles.contains(inputRole);
    }
}
