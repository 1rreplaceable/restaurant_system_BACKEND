package com.example.restaurant_system_backend.auth;

import com.example.restaurant_system_backend.auth.dto.LoginRequest;
import com.example.restaurant_system_backend.auth.dto.LoginResponse;
import com.example.restaurant_system_backend.auth.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인 처리
     *
     * @param loginRequest 로그인 요청 정보 (username, password)
     * @return 로그인 성공 시 JWT 토큰과 사용자 역할을 반환
     */
    public LoginResponse login(LoginRequest loginRequest) {
        // 사용자 이름으로 사용자 조회
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        // 비밀번호 확인
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // JWT 토큰 생성
        String token = jwtService.generateToken(user.getUsername());
        return new LoginResponse(token, user.getRole(), user.getRestaurant().getId());
    }
}
