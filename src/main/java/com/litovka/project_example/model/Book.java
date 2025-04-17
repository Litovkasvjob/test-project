package com.litovka.project_example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import static com.litovka.project_example.constants.ProjectConstants.AUTHOR_MAX_LENGTH;
import static com.litovka.project_example.constants.ProjectConstants.TITLE_MAX_LENGTH;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = TITLE_MAX_LENGTH)
    private String title;

    @Column(name = "author", nullable = false, length = AUTHOR_MAX_LENGTH)
    private String author;

    @Column(name = "published_year", nullable = false)
    private Integer year;
}