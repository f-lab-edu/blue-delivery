package com.delivery.shop.category;

public class CategoryData {
    private Long id;
    private String name;
    private String enumName;
    
    public CategoryData() {
    }
    
    public CategoryData(Long id, String name, String enumName) {
        this.id = id;
        this.name = name;
        this.enumName = enumName;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEnumName() {
        return enumName;
    }
}
