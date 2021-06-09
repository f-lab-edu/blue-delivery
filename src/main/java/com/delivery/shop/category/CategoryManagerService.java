package com.delivery.shop.category;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.delivery.shop.shop.SearchedShopData;

@Service
public class CategoryManagerService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryManagerService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<CategoryData> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public void updateCategories() {
        categoryRepository.update(Arrays.asList(Category.values()));
    }
    
    public List<SearchedShopData> getShopsByCategory(Long id, Integer offset) {
        return categoryRepository.findShopsByCategoryId(id, offset);
    }
}
