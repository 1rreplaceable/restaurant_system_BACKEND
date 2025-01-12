package com.example.restaurant_system_backend.categories;

import com.example.restaurant_system_backend.restaurants.Restaurant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Category(String name, Restaurant restaurant, int seq) {
        this.name = name;
        this.restaurant = restaurant;
        this.seq = seq;
    }
    public void updateSequence(int newSeq) {
        this.seq = newSeq;
    }
}
