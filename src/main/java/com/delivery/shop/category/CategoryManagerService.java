package com.delivery.shop.category;

import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

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
        // TODO 카테고리 이름의 다국어처리 i18n
        return categoryRepository.findAll().stream()
                .map(Category::toResponse)
                .collect(toList());
    }
    
    public void updateCategories() {
        categoryRepository.update(Arrays.asList(Category.values()));
    }
    
    public List<SearchedShopData> getShopsByCategory(SearchShopByCategoryParam param) {
        return categoryRepository.findShopsByCategoryId(param).stream()
                .map(data -> data.setNow(param.getNow())) // 가져온 객체에 조회시점의 시간 설정
                .filter(not(SearchedShopData::isClosingDay)) // 휴무가 아닌 가게
                .sorted(comparing(SearchedShopData::isOpening).reversed()) // 영업시간인 가게가 앞으로
                .collect(toList());
    }
}
