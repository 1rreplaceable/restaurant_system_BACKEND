package com.example.restaurant_system_backend.restaurant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int tableCount;

    private String location;

    private String phone;

    @CreationTimestamp // Hibernate가 자동으로 생성 시간 설정
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Restaurant(String name, String username, String password, int tableCount, String location, String phone) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.tableCount = tableCount;
        this.location = location;
        this.phone = phone;
    }
}
