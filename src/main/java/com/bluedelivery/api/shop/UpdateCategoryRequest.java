package com.bluedelivery.api.shop;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Size;

public class UpdateCategoryRequest {
    @Size(min = 1, max = 4)
    private List<Long> categoryIds;
    
    public UpdateCategoryRequest() {
    }
    
    public UpdateCategoryRequest(Long ... ids) {
        this.categoryIds = Arrays.asList(ids);
    }
    
    public List<Long> getCategoryIds() {
        return categoryIds;
    }
}
