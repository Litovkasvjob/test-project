package com.litovka.project_example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(max = 50, message = "Author must not exceed 50 characters")
    private String author;

    @NotNull(message = "Published year is mandatory")
    @Min(value = 1900, message = "Published year must be no earlier than 1900")
    @Max(value = 2100, message = "Published year must be no later than 2100")
    private Integer publishedYear;
}