package com.delivery.shop.category;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delivery.shop.shop.SearchedShopData;

@Service
public class CategoryManagerService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryManagerService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<CategoryData> getAllCategories() {
        // TODO 카테고리 이름의 다국어처리 i18n
        return categoryRepository.findAll().stream()
                .map(Category::toResponse)
                .collect(Collectors.toList());
    }
    
    public void updateCategories() {
        categoryRepository.update(Arrays.asList(Category.values()));
    }
    
    public List<SearchedShopData> getShopsByCategory(Long id, Integer offset) {
        return categoryRepository.findShopsByCategoryId(id, offset);
    }
}
