package com.example.restaurant_system_backend.orders;

import com.example.restaurant_system_backend.menus.Menu;
import com.example.restaurant_system_backend.restaurants.Restaurant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "order_type", nullable = false, length = 50)
    private OrderType orderType;
}
