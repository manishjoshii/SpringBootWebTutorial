package com.manishjoshi.SpringBootWebTutorial.DTO;

import java.time.Instant;

public class EmployeeDTO {

    private int id;
    private String name;
    private int age;
    private double salary;
    private Instant dateJoined;

    public EmployeeDTO(int id, String name, int age, double salary, Instant dateJoined) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.dateJoined = dateJoined;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Instant getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Instant dateJoined) {
        this.dateJoined = dateJoined;
    }
}
