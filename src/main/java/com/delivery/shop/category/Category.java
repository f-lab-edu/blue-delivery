package com.delivery.shop.category;

import java.util.List;
import java.util.stream.Collectors;

public enum Category {
    
    KOREAN(1L, "한식"),
    CHINESE(2L, "중식"),
    JAPANESE(3L, "일식"),
    CHICKEN(4L, "치킨"),
    PIZZA(5L, "피자"),
    FAST_FOOD(6L, "패스트푸드");
    
    Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    private Long id;
    private String name;
    
    public static List<Category> from(List<String> dto) {
        return dto.stream()
                .map(Category::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
