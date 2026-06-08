package com.manishjoshi.SpringBootWebTutorial.controllers;

import com.manishjoshi.SpringBootWebTutorial.dto.EmployeeDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.manishjoshi.SpringBootWebTutorial.exceptions.ResourceNotFoundException;
import com.manishjoshi.SpringBootWebTutorial.services.EmployeeService;
import jakarta.validation.Valid;
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
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found, ID: " + id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO result = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable UUID id) {
        EmployeeDTO result = employeeService.updateEmployeeById(employeeDTO, id);
        return ResponseEntity.ok(result);
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
