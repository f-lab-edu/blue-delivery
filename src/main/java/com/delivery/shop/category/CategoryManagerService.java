package com.delivery.shop.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delivery.exception.NotFoundIdException;

@Service
public class CategoryManagerService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryManagerService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public void addCategory(String name) {
        categoryRepository.save(name);
    }
    
    public void renameCategory(RenameCategoryParam param) {
        int count = categoryRepository.updateName(param);
        if (count == 0) {
            throw new IllegalArgumentException("category to rename is not found");
        }
    }
    
    public void deleteCategory(String name) {
        int count = categoryRepository.deleteByName(name);
        if (count == 0) {
            throw new IllegalArgumentException("category to delete is not found");
        }
    }
}
