package com.example.restaurant_system_backend.auth;
import com.example.restaurant_system_backend.auth.dto.LoginRequest;
import com.example.restaurant_system_backend.auth.dto.LoginResponse;
import com.example.restaurant_system_backend.auth.dto.UserRequest;
import com.example.restaurant_system_backend.auth.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.username());

        if (userOptional.isPresent() && passwordEncoder.matches(loginRequest.password(), userOptional.get().getPassword())) {
            String token = jwtService.generateToken(userOptional.get().getUsername());
            return new LoginResponse(token, userOptional.get().getRole());
        }

        throw new IllegalArgumentException("Invalid username or password");
    }

    public void register(UserRequest userRequest) {
        if (userRepository.findByUsername(userRequest.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = userRequest.toEntity(passwordEncoder);
        userRepository.save(user);
    }
}
