package com.delivery.shop.category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Categories {
    
    private List<Category> categories;
    
    public Categories() {
        this.categories = new ArrayList<>();
    }
    
    public Categories(List<Category> categories) {
        this.categories = categories;
    }
    
    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }
    
    public void updateAll(Collection<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
    }
}

