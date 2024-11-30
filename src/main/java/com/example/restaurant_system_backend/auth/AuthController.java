package com.example.restaurant_system_backend.auth;

import com.example.restaurant_system_backend.auth.dto.LoginRequest;
import com.example.restaurant_system_backend.auth.dto.LoginResponse;
import com.example.restaurant_system_backend.auth.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
        authService.register(userRequest);
        return ResponseEntity.ok("User registered successfully");
    }
}