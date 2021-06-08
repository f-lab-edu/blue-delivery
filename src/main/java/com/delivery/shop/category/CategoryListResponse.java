package com.delivery.shop.category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryListResponse {
    
    private List<String> categories;
    
    public CategoryListResponse(List<Category> allCategories) {
        this.categories = allCategories.stream()
                .map(x -> x.getName())
                .collect(Collectors.toList());
    }
    
    public List<String> getCategories() {
        return categories;
    }
}
