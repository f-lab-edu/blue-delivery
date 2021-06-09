package com.delivery.shop.category;

public class CategoryResponse {
    private String name;
    private String enumName;
    
    public CategoryResponse() {
    }
    
    public CategoryResponse(String name, String enumName) {
        this.name = name;
        this.enumName = enumName;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEnumName() {
        return enumName;
    }
}
