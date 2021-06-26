package com.delivery.shop.category;

import java.util.List;
import java.util.stream.Collectors;

import com.delivery.shop.shop.Shop;

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
    private List<Shop> shops;
    
    public Category(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }
    
    public List<Shop> getShops() {
        return shops;
    }
    
    public CategoryData toResponse() {
        return new CategoryData(this.id, this.name);
    }
    
}
