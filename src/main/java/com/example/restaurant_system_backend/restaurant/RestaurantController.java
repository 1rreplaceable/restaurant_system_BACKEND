package com.example.restaurant_system_backend.restaurant;

import com.example.restaurant_system_backend.restaurant.dto.RestaurantRequest;
import com.example.restaurant_system_backend.restaurant.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/register")
    public ResponseEntity<RestaurantResponse> registerRestaurant(@RequestBody RestaurantRequest request) {
        RestaurantResponse response = restaurantService.registerRestaurant(request);
        return ResponseEntity.ok(response);
    }
}
