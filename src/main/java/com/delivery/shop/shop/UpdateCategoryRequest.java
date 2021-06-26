package com.delivery.shop.shop;

import java.util.List;

import javax.validation.constraints.Size;

public class UpdateCategoryRequest {
    @Size(min = 1, max = 4)
    private List<Long> categoryIds;
    
    public UpdateCategoryRequest() {
    }
    
    public UpdateCategoryRequest(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
    
    public List<Long> getCategoryIds() {
        return categoryIds;
    }
    
    public void setCategoryIds(List<Long> typeName) {
        this.categoryIds = typeName;
    }
}
