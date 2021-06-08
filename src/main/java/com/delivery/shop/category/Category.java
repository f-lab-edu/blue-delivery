package com.delivery.shop.category;

public class Category {
    private int id;
    private String name;
    
    Category(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
