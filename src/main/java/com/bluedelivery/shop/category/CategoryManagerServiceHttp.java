package com.bluedelivery.shop.category;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        return categoryRepository.findAllCategories();
    }
    
    @CacheEvict(value = "categories", allEntries = true)
    public Category addCategory(CreateCategoryParam param) {
        Category category = new Category(param.getName());
        return categoryRepository.addCategory(category);
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
        return categoryRepository.findCategoriesById(categoryIds);
    }
    
}
