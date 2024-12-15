package com.example.restaurant_system_backend.categories;

import com.example.restaurant_system_backend.categories.dto.CategoryReorderRequest;
import com.example.restaurant_system_backend.categories.dto.CategoryRequest;
import com.example.restaurant_system_backend.categories.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<CategoryResponse>> getCategoriesByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(categoryService.getCategoriesByRestaurant(restaurantId));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PutMapping("/reorder")
    public ResponseEntity<Void> reorderCategories(@RequestBody List<CategoryReorderRequest> reorderRequests) {
        categoryService.reorderCategories(reorderRequests);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
