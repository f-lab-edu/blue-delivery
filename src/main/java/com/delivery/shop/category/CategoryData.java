package com.delivery.shop.category;

public class CategoryData {
    private Long id;
    private String name;
    
    public CategoryData() {
    }
    
    public CategoryData(Long id, String name) {
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
