package com.delivery.shop.category;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class CategoryManagerServiceHttp implements CategoryManagerService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryManagerServiceHttp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Cacheable(value = "categories", cacheManager = "caffeineCacheManager")
    public List<Category> getAllCategories() {
        // TODO 카테고리 이름의 다국어처리 i18n
        List<Category> allCategories = categoryRepository.findAllCategories();
        return allCategories;
    }
    
    @CacheEvict(value = "categories", allEntries = true)
    public void addCategory(CreateCategoryParam param) {
        try {
            categoryRepository.addCategory(param);
        } catch (RuntimeException exception) {
            throw new DuplicateKeyException(param.getName() + " is already exists");
        }
    }
    
    @CacheEvict(value = "categories", allEntries = true)
    public void deleteCategory(Long id) {
        categoryRepository.deleteCategoryById(id);
    }
    
    @CacheEvict(value = "categories", allEntries = true)
    public void editCategory(EditCategoryParam param) {
        categoryRepository.updateCategoryById(param);
    }
    
}
