package com.litovka.project_example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static com.litovka.project_example.constants.ProjectConstants.PASSWORD_MAX_LENGTH;
import static com.litovka.project_example.constants.ProjectConstants.PASSWORD_MIN_LENGTH;
import static com.litovka.project_example.constants.ProjectConstants.USERNAME_MAX_LENGTH;

@Data
public class UserDTO {

    @NotBlank(message = "Username is mandatory")
    @Size(max = USERNAME_MAX_LENGTH, message = "Username must be at most " + USERNAME_MAX_LENGTH + " characters long")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH,
            message = "Password must have at least " + PASSWORD_MIN_LENGTH +
                    " and at most " + PASSWORD_MAX_LENGTH + " characters")
    private String password;
}