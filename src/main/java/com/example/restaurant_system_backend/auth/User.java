package com.example.restaurant_system_backend.auth;

import com.example.restaurant_system_backend.restaurants.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // 'TABLE' or 'ADMIN'

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public User(String username, String password, String role, Restaurant restaurant) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.restaurant = restaurant;
    }

    public User(String username, String encode, String role) {
    }
}
