package com.example.restaurant_system_backend.categories;

import com.example.restaurant_system_backend.categories.dto.CategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT MAX(c.seq) FROM Category c WHERE c.restaurant.id = :restaurantId")
    Optional<Integer> findMaxSeqByRestaurantId(@Param("restaurantId") Long restaurantId);
    List<Category> findByRestaurantIdOrderBySeqAsc(Long restaurantId);
}
