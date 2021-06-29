package com.delivery.shop.category;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.delivery.shop.shop.Shop;

@Repository
public interface CategoryRepository {
    List<Category> findAllCategories();
    
    void update(List<Category> categories);
    
    List<Shop> findShopsByCategoryId(SearchShopByCategoryParam param);
}
