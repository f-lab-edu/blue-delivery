package com.bluedelivery.domain.category;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
    KOREAN(1),
    CHINESE(2),
    JAPANESE(3),
    CHICKEN(4),
    PIZZA(5),
    FAST_FOOD(6);
*/
@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String name;
    
    public Category() {
    }
    
    public Category(String name) {
        this.name = name;
    }
    
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }
    
    public void changeName(String newName) {
        this.name = newName;
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
        return Objects.equals(name, category.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
