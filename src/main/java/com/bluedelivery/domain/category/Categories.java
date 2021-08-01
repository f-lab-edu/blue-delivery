package com.bluedelivery.domain.category;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import com.bluedelivery.domain.shop.ShopCategory;

@Embeddable
public class Categories {
    
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private Set<ShopCategory> categories = new HashSet<>();
    
    public Categories() {
    }
    
    public Collection<ShopCategory> getCategories() {
        return Collections.unmodifiableSet(categories);
    }
    
    public boolean updateAll(Collection<ShopCategory> categories) {
        this.categories.clear();
        return this.categories.addAll(categories);
    }
}

