package com.litovka.project_example.service;

import com.litovka.project_example.dto.UserDTO;
import com.litovka.project_example.model.User;
import com.litovka.project_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String registerUser(UserDTO userDTO) {
        Optional<User> existingUserByUsername = userRepository.findByUsername(userDTO.getUsername());
        if (existingUserByUsername.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        Optional<User> existingUserByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hash password

        userRepository.save(user);
        return "User registered successfully";
    }


    public String deleteUser(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid user ID.");
        }
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with ID " + id + " has been successfully deleted.";
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }

    public String updateUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(userDTO.getId());
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userDTO.getId() + " not found");
        }

        User user = existingUser.get();

        // Update fields if they are provided in the DTO
        if (userDTO.getUsername() != null && !userDTO.getUsername().isBlank()) {
            if (userRepository.existsByUsername(userDTO.getUsername()) &&
                    !user.getUsername().equals(userDTO.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
            user.setUsername(userDTO.getUsername());
        }

        if (userDTO.getEmail() != null && !userDTO.getEmail().isBlank()) {
            if (userRepository.existsByEmail(userDTO.getEmail()) &&
                    !user.getEmail().equals(userDTO.getEmail())) {
                throw new IllegalArgumentException("Email already registered");
            }
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hash password
        }

        userRepository.save(user);
        return "User with ID " + user.getId() + " updated successfully";
    }

}