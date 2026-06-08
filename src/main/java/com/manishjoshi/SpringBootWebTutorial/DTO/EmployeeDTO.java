package com.manishjoshi.SpringBootWebTutorial.dto;

import com.manishjoshi.SpringBootWebTutorial.annotations.validations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private UUID id;

    @NotBlank(message = "Name can not be blank")
    @Size(min = 2, max = 15, message = "Length of name should be in between 2 to 15")
    private String name;

    @NotBlank(message = "Email can not be blank")
    @Email(message = "Email should be valid")
    @Size(min = 4, max = 25, message = "Length of email should be in between 2 to 25")
    private String email;

    @NotNull(message = "Age can't be null")
    @Max(value = 60, message = "Age of employee should be less than 60")
    @Min(value = 18, message = "Age of employee should be greater than 18")
    private Integer age;

    @NotNull(message = "Salary can't be null")
    @Positive(message = "Salary should be non zero positive number")
    @DecimalMin(value = "400000.00", message = "Minimum Salary should start from 400000.00")
    private Double salary;

    @NotBlank(message = "Role should not be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$", message = "Role should be either 'USER' Or 'ADMIN'")
    @EmployeeRoleValidation
    private String role;

    private Instant createdAt;
    private Instant updatedAt;

}
