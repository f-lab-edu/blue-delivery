package com.bluedelivery.application.category.adapter;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bluedelivery.application.category.CategoryManagerService;
import com.bluedelivery.application.category.CategoryNotFoundException;
import com.bluedelivery.application.category.CreateCategoryParam;
import com.bluedelivery.application.category.EditCategoryParam;
import com.bluedelivery.domain.category.Category;
import com.bluedelivery.domain.category.CategoryRepository;

@Service
public class CategoryManagerServiceHttp implements CategoryManagerService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryManagerServiceHttp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Cacheable(value = "categories", cacheManager = "caffeineCacheManager")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    @CacheEvict(value = "categories", allEntries = true)
    public Category addCategory(CreateCategoryParam param) {
        Category category = new Category(param.getName());
        return categoryRepository.save(category);
    }
    
    @CacheEvict(value = "categories", allEntries = true)
    public long deleteCategory(Long id) {
        return categoryRepository.deleteCategoryById(id);
    }
    
    @CacheEvict(value = "categories", allEntries = true)
    public void editCategory(EditCategoryParam param) {
        Category category = categoryRepository.findById(param.getId())
                .orElseThrow(() -> new CategoryNotFoundException());
        category.changeName(param.getName());
    }
    
    @Override
    public List<Category> getCategoriesById(List<Long> categoryIds) {
        List<Category> categories = categoryRepository.findCategoriesByIdIn(categoryIds);
        if (categories.size() != categoryIds.size()) {
            throw new CategoryNotFoundException();
        }
        return categories;
    }
    
}
