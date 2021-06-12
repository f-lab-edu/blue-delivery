package com.delivery.shop.category;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.delivery.shop.shop.SearchedShopData;

@Repository
public interface CategoryRepository {
    List<Category> findAll();
    
    void update(List<Category> categories);
    
    List<SearchedShopData> findShopsByCategoryId(Long id, Integer offset);
}
