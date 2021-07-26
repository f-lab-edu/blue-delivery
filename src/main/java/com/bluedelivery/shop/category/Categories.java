package com.bluedelivery.shop.category;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Categories {
    
    private Set<Category> categories = new HashSet<>();
    
    public Categories() {
    }
    
    public Collection<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }
    
    public boolean updateAll(Collection<Category> categories) {
        this.categories.clear();
        return this.categories.addAll(categories);
    }
}

