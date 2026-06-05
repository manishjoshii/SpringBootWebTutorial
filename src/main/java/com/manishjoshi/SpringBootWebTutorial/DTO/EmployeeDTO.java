package com.manishjoshi.SpringBootWebTutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private UUID id;
    private String name;
    private String email;
    private int age;
    private Instant createdAt;
    private Instant updatedAt;

}
