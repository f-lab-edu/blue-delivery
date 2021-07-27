package com.bluedelivery.domain.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bluedelivery.application.category.EditCategoryParam;
import com.bluedelivery.application.shop.SearchShopByCategoryParam;
import com.bluedelivery.domain.shop.Shop;

@Repository
public interface CategoryRepository {
    List<Category> findAllCategories();
    
    List<Shop> findShopsByCategoryId(SearchShopByCategoryParam param);
    
    Category addCategory(Category category);
    
    long deleteCategoryById(Long id);
    
    void updateCategoryById(EditCategoryParam param);
    
    Optional<Category> findById(Long id);
    
    List<Category> findCategoriesById(List<Long> categoryIds);
}
