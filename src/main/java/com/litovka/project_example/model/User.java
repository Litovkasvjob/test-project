package com.litovka.project_example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    private static final int USERNAME_MAX_LENGTH = 50;
    private static final int EMAIL_MAX_LENGTH = 100;
    private static final int PASSWORD_MAX_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = USERNAME_MAX_LENGTH)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = EMAIL_MAX_LENGTH)
    private String email;

    @Column(name = "password", nullable = false, length = PASSWORD_MAX_LENGTH)
    private String password;
}