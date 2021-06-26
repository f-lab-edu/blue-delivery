package com.delivery.shop.category;

import static java.lang.Boolean.*;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.delivery.shop.category.CreateCategoryParam.CreateCategoryRequest;
import com.delivery.shop.shop.Shop;

@Service
public class CategoryManagerServiceImpl implements CategoryManagerService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryManagerServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @Cacheable(value = "categories", cacheManager = "concurrentMapCacheManager")
    public List<Category> getAllCategories() {
        // TODO 카테고리 이름의 다국어처리 i18n
        List<Category> allCategories = categoryRepository.findAllCategories();
        return allCategories;
    }
    
    public List<Shop> getShopsByCategory(SearchShopByCategoryRequest param) {
        LocalDateTime when = param.getNow();
        return categoryRepository.findShopsByCategoryId(param).stream()
                .filter(shop -> !shop.isClosingAt(when.toLocalDate())) // 휴무가 아닌 가게만 선택
                .sorted((o1, o2) -> compare(o2.isOpeningAt(when), o1.isOpeningAt(when))) // 영업중 가게(true) 순 정렬
                .collect(toList());
    }
    
    @CacheEvict(value = "categories", allEntries = true)
    public void addCategory(CreateCategoryRequest param) {
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
