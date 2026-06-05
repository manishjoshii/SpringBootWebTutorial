package com.manishjoshi.SpringBootWebTutorial.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manishjoshi.SpringBootWebTutorial.dto.EmployeeDTO;
import com.manishjoshi.SpringBootWebTutorial.entities.EmployeeEntity;
import com.manishjoshi.SpringBootWebTutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(UUID id) {
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()   // use of stream Api to iterate on employee entities list
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class)) // use map and one by one convert entity to DTO using modelmapper
                .collect(Collectors.toList()); // at end collect and convert it to list and return
    }

    public boolean isExistsByEmployeeId(UUID id) {
        return employeeRepository.existsById(id);
    }

    public EmployeeDTO createEmployee(JsonNode body) {
        try {
            EmployeeEntity employeeEntity = objectMapper.treeToValue(body, EmployeeEntity.class);
            EmployeeEntity employee = employeeRepository.save(employeeEntity);
            return modelMapper.map(employee, EmployeeDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON input", e);
        }
    }

    public EmployeeDTO updateEmployeeById(JsonNode body, UUID id) {
        // 1. Check if the record already exists in the database
        boolean isExisting = isExistsByEmployeeId(id);

        // 2. Initialize the entity reference
        EmployeeEntity employeeEntity;

        if (isExisting) {
            // If it exists, pull the current managed entity state from the DB
            employeeEntity = employeeRepository.findById(id).get();
            // Ensure the ID stays locked to the path variable
            employeeEntity.setId(id);
        } else {
            // If it's brand new, create an entirely fresh entity instance
            employeeEntity = new EmployeeEntity();
        }

        try {
            // 3. Overwrite/populate the fields from your JSON body
            objectMapper.readerForUpdating(employeeEntity).readValue(body);

            // 4. Save the entity
            EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

            // 5. Map back to DTO and return
            return modelMapper.map(savedEmployee, EmployeeDTO.class);

        } catch (IOException e) {
            throw new RuntimeException("Failed to update/create employee JSON input", e);
        }
    }

    public Optional<EmployeeDTO> patchEmployeeById(JsonNode body, UUID id) {
        // 1. Find the entity or return null if it doesn't exist
        return employeeRepository.findById(id).map(employeeEntity -> {
            try {
                // 2. Merge the incoming JSON patch into the existing database entity
                objectMapper.readerForUpdating(employeeEntity).readValue(body);
                // 3. Persist the updated entity to the database
                EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
                // 4. Use ModelMapper to convert the saved Entity to a DTO
                return modelMapper.map(updatedEmployee, EmployeeDTO.class);
            } catch (Exception e) {
                throw new RuntimeException("Failed to patch employee JSON input", e);
            }
        });
    }

    public boolean deleteEmployeeById(UUID id) {
        boolean exists = isExistsByEmployeeId(id);
        if (!exists) return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public boolean deleteEmployeeByEmail(String email) {
        boolean exists = employeeRepository.existsByEmail(email);
        if (!exists) return false;
        return employeeRepository.deleteByEmail(email) > 0;
    }
}
