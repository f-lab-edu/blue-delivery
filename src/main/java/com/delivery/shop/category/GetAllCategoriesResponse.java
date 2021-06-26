package com.delivery.shop.category;

import java.util.List;

public class GetAllCategoriesResponse {
    private List<CategoryData> allCategories;
    
    public GetAllCategoriesResponse(List<CategoryData> allCategories) {
        this.allCategories = allCategories;
    }
    
    public List<CategoryData> getAllCategories() {
        return allCategories;
    }
}
