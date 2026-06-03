package com.manishjoshi.SpringBootWebTutorial.controllers;

import com.manishjoshi.SpringBootWebTutorial.DTO.EmployeeDTO;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping(path = "/api")
public class EmployeeController {

    @GetMapping(path = "/hello")
    public String getHello() {
        return "Hello world";
    }

    @GetMapping(path = "/employee/{employeeId}")
    public ArrayList<EmployeeDTO> getEmployeesById(@PathVariable int employeeId) {
        return new ArrayList<>(
                Arrays.asList(new EmployeeDTO(employeeId, "Manish", 25, 30000, Instant.now()),
                        new EmployeeDTO(employeeId + 1, "Namrata", 25, 62000, Instant.now()))
        );
    }

    @GetMapping(path = "/employee")
    public EmployeeDTO getEmployeeById(@RequestParam(required = false, name = "id") Integer employeeId) {
        int id = employeeId != null ? (int) employeeId : 1;
        return new EmployeeDTO(id, "Manish", 25, 30000, Instant.now());
    }

    @PostMapping(path = "/employee")
    public EmployeeDTO setEmployee(@RequestBody EmployeeDTO employee) {
        return employee;
    }

    @PutMapping(path = "/employee")
    public EmployeeDTO setEmployee(@RequestBody JsonNode body) {
        String name = body.get("name").asString("Rahul");
        return new EmployeeDTO(1, name, 25, 30000, Instant.now());
    }
}
