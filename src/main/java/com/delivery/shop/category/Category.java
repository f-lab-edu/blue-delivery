package com.delivery.shop.category;

import java.util.List;

/*
    KOREAN(1),
    CHINESE(2),
    JAPANESE(3),
    CHICKEN(4),
    PIZZA(5),
    FAST_FOOD(6);
*/
public class Category {
    
    private Long id;
    private String name;
    private List<Long> shops;
    
    public Category() {
    }
    
    public Category(String name) {
        this.name = name;
    }
    
    public Category(Long id, String name, List<Long> shops) {
        this.id = id;
        this.name = name;
        this.shops = shops;
    }
    
    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }
    
    public List<Long> getShops() {
        return shops;
    }
    
    public CategoryData toResponse() {
        return new CategoryData(this.id, this.name);
    }
    
}
