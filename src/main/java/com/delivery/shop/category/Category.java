package com.delivery.shop.category;

import java.util.Objects;

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
    
    public Category() {
    }
    
    public Category(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Category category = (Category) obj;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    
    public void changeName(String newName) {
        this.name = newName;
    }
}
