package com.example.restaurant_system_backend.restaurant.dto;

import com.example.restaurant_system_backend.restaurant.Restaurant;

public record RestaurantRequest(
        String name,
        String username,
        String password,
        int tableCount,
        String location,
        String phone
) {
    // Restaurant 엔티티로 변환하는 메서드
    public Restaurant toEntity(String encodedPassword) {
        return new Restaurant(
                name,
                username,
                encodedPassword,
                tableCount,
                location,
                phone
        );
    }
}

