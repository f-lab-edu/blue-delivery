package com.delivery.shop.category;

import java.util.List;
import java.util.stream.Collectors;

public enum Category {
    
    KOREAN(1L),
    CHINESE(2L),
    JAPANESE(3L),
    CHICKEN(4L),
    PIZZA(5L),
    FAST_FOOD(6L);
    
    Category(Long id) {
        this.id = id;
    }
    
    private Long id;
    
    public static List<Category> from(List<String> dto) {
        return dto.stream()
                .map(Category::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }
    
    public Long getId() {
        return id;
    }
    
    public static CategoryData toResponse(Category category) {
        return new CategoryData(category.id, category.toString());
    }
}
