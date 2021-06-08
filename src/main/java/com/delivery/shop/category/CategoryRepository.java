package com.delivery.shop.category;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository {
    List<Category> findAll();
    
    void save(String name);
    
    int updateName(RenameCategoryParam renameCategoryParam);
    
    int deleteByName(String name);
}
