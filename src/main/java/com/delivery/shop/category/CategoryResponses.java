package com.delivery.shop.category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryResponses {
    
    private List<CategoryResponse> categories;
    
    public CategoryResponses() {
    }
    
    public CategoryResponses(List<Category> categories) {
        this.categories = categories.stream()
                .map(Category::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<CategoryResponse> getCategories() {
        return categories;
    }
}
