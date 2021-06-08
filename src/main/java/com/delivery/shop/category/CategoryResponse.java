package com.delivery.shop.category;

public class CategoryResponse {
    private Long id;
    private String name;
    
    public CategoryResponse() {
    }
    
    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
