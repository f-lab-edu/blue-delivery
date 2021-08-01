package com.bluedelivery.domain.shop;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.bluedelivery.domain.category.Category;

@Entity
public class ShopCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Shop shop;
    
    @ManyToOne
    private Category category;
    
    protected ShopCategory() {
    }
    
    public ShopCategory(Shop shop, Category category) {
        this.shop = shop;
        this.category = category;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ShopCategory that = (ShopCategory) obj;
        return Objects.equals(shop, that.shop) && Objects.equals(category, that.category);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
