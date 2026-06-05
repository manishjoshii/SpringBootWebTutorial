package com.manishjoshi.SpringBootWebTutorial.controllers;

import com.manishjoshi.SpringBootWebTutorial.dto.EmployeeDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.manishjoshi.SpringBootWebTutorial.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable UUID id) {
        Optional<EmployeeDTO> employee = employeeService.getEmployeeById(id);
        return employee
                .map(ResponseEntity::ok)   // ResponseEntity.ok(employee)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody JsonNode body) {
        EmployeeDTO employeeDTO = employeeService.createEmployee(body);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody JsonNode body, @PathVariable UUID id) {
        EmployeeDTO employeeDTO = employeeService.updateEmployeeById(body, id);
        return ResponseEntity.ok(employeeDTO);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> patchEmployeeById(@RequestBody JsonNode body, @PathVariable UUID id) {
        return employeeService.patchEmployeeById(body, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable UUID id) {
        boolean deleted = employeeService.deleteEmployeeById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteEmployeeByEmail(@RequestParam String email) {
        boolean deleted = employeeService.deleteEmployeeByEmail(email);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
