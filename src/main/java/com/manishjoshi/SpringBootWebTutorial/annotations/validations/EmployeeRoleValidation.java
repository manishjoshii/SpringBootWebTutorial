package com.manishjoshi.SpringBootWebTutorial.annotations.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {

    String message() default "Role should be either 'USER' Or 'ADMIN'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
