package com.example.restaurant_system_backend.auth.dto;

import com.example.restaurant_system_backend.auth.User;
import com.example.restaurant_system_backend.restaurant.Restaurant;

public record UserRequest(String username, String role, Restaurant restaurant) {

    // User 엔티티로 변환하는 메서드
    public User toEntity(String encodedPassword) {
        return new User(username, encodedPassword, role, restaurant);
    }
}