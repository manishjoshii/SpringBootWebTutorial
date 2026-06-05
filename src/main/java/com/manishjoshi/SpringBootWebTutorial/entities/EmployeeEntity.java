package com.manishjoshi.SpringBootWebTutorial.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // <--- Enable listener for auditing
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private Integer age;

    private Double salary;

    private String role;

    @CreatedDate // <--- Automatically generates timestamp on insertion
    @Column(nullable = false, updatable = false) // <--- Prevents modifications on updates
    private Instant createdAt;

    @LastModifiedDate // <--- Automatically updates timestamp on every SQL UPDATE
    @Column(nullable = false)
    private Instant updatedAt;
}
