package com.example.restaurant_system_backend.restaurant;

import com.example.restaurant_system_backend.auth.User;
import com.example.restaurant_system_backend.auth.UserRepository;
import com.example.restaurant_system_backend.auth.dto.UserRequest;
import com.example.restaurant_system_backend.restaurant.dto.RestaurantRequest;
import com.example.restaurant_system_backend.restaurant.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RestaurantResponse registerRestaurant(RestaurantRequest request) {
        // 사용자 이름 중복 확인
        if (restaurantRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.password());

        // 식당 정보 저장
        Restaurant restaurant = request.toEntity(encodedPassword);
        restaurantRepository.save(restaurant);

        // 주방 관리자 계정 생성
        UserRequest adminRequest = new UserRequest(
                request.username() + "_admin",
                "ADMIN",
                restaurant
        );
        User admin = adminRequest.toEntity(encodedPassword);
        userRepository.save(admin);

        // 테이블 계정 생성
        for (int i = 1; i <= request.tableCount(); i++) {
            UserRequest tableRequest = new UserRequest(
                    request.username() + i,
                    "TABLE",
                    restaurant
            );
            User tableUser = tableRequest.toEntity(encodedPassword);
            userRepository.save(tableUser);
        }

        return new RestaurantResponse("Restaurant registered successfully with " + request.tableCount() + " table accounts");
    }
}
