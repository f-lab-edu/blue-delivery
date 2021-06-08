package com.delivery.shop.category;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryManagerService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryManagerService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public CategoryResponses getAllCategories() {
        List<Category> all = categoryRepository.findAll();
        return new CategoryResponses(all);
    }
    
    public void updateCategory() {
        categoryRepository.update(Arrays.asList(Category.values()));
    }
}
