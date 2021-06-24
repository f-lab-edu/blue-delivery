package com.delivery.shop.category;

import java.util.List;

public class GetAllCategories {
    private List<CategoryData> allCategories;
    
    public GetAllCategories(List<CategoryData> allCategories) {
        this.allCategories = allCategories;
    }
    
    public List<CategoryData> getAllCategories() {
        return allCategories;
    }
}
