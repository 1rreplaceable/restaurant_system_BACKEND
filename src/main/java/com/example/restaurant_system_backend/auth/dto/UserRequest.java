package com.example.restaurant_system_backend.auth.dto;

import com.example.restaurant_system_backend.auth.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public record UserRequest(String username, String password, String role) {

    public User toEntity(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), role);
    }
}