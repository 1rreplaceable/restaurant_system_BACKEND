package com.example.restaurant_system_backend.categories;

import com.example.restaurant_system_backend.categories.dto.CategoryReorderRequest;
import com.example.restaurant_system_backend.categories.dto.CategoryRequest;
import com.example.restaurant_system_backend.categories.dto.CategoryResponse;
import com.example.restaurant_system_backend.restaurants.Restaurant;
import com.example.restaurant_system_backend.restaurants.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    public Category createCategory(CategoryRequest request) {
        Restaurant restaurant = restaurantRepository.findById(request.restaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));

        int maxSeq = categoryRepository.findMaxSeqByRestaurantId(request.restaurantId()).orElse(0);
        Category category = new Category(request.name(), restaurant, maxSeq + 1);

        return categoryRepository.save(category);
    }

    public List<CategoryResponse> getCategoriesByRestaurant(Long restaurantId) {
        List<Category> categories = categoryRepository.findByRestaurantIdOrderBySeqAsc(restaurantId);

        return categories.stream()
                .map(CategoryResponse::new)
                .toList();
    }

    @Transactional
    public void reorderCategories(List<CategoryReorderRequest> requests) {
        for (CategoryReorderRequest request : requests) {
            Category category = categoryRepository.findById(request.id())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            category.updateSequence(request.seq());
            categoryRepository.save(category);
        }
    }
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 ID의 카테고리가 존재하지 않습니다: " + id);
        }
        categoryRepository.deleteById(id);
    }

}
