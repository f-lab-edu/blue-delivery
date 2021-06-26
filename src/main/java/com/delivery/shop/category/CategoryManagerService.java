package com.delivery.shop.category;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.delivery.shop.shop.Shop;

@Service
public class CategoryManagerService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryManagerService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<Category> getAllCategories() {
        // TODO 카테고리 이름의 다국어처리 i18n
        return categoryRepository.findAllCategories();
    }
    
    public List<Shop> getShopsByCategory(SearchShopByCategoryParam param) {
        LocalDateTime when = param.getNow();
        return categoryRepository.findShopsByCategoryId(param).stream()
                .filter(shop -> !shop.isClosingAt(when.toLocalDate())) // 휴무가 아닌 가게만 선택
                .sorted((o1, o2) -> Boolean.compare(o2.isOpeningAt(when), o1.isOpeningAt(when))) // 영업중 가게(true) 순 정렬
                .collect(toList());
    }
    
}
