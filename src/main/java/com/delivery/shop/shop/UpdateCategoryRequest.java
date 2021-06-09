package com.delivery.shop.shop;

import java.util.List;

import javax.validation.constraints.Size;

public class UpdateCategoryRequest {
    @Size(min = 1, max = 4)
    private List<String> typeName;
    
    public UpdateCategoryRequest() {
    }
    
    public UpdateCategoryRequest(List<String> typeName) {
        this.typeName = typeName;
    }
    
    public List<String> getTypeName() {
        return typeName;
    }
    
    public void setCategoryIds(List<String> typeName) {
        this.typeName = typeName;
    }
}
