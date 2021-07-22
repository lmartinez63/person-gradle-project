package com.training.examinterview.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Data
@Document
public class Person {
    @Id
    @JsonIgnore
    private String id;

    @Indexed
    private String dni;

    @NotBlank(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Min(18)
    @Max(99)
    private int age;

    public Person(String dni, String name, int age) {
        this.dni = dni;
        this.name = name;
        this.age = age;
    }
}
