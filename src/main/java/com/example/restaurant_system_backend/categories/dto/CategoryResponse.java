package com.example.restaurant_system_backend.categories.dto;

import com.example.restaurant_system_backend.categories.Category;

public record CategoryResponse(
        Long id,
        String name,
        int seq
) {
    public CategoryResponse(Category category) {
        this(category.getId(), category.getName(), category.getSeq());
    }
}
