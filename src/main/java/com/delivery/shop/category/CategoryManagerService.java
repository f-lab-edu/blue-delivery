package com.delivery.shop.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delivery.shop.category.CreateCategoryParam.CreateCategoryRequest;
import com.delivery.shop.shop.Shop;

@Service
public interface CategoryManagerService {
    
    List<Category> getAllCategories();
    
    List<Shop> getShopsByCategory(SearchShopByCategoryRequest param);
    
    void addCategory(CreateCategoryRequest param);
    
    void deleteCategory(Long id);
    
    void editCategory(EditCategoryParam param);
    
}
