package com.delivery.shop.category;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.shop.shop.SearchedShopData;

@RestController
@RequestMapping("/categories")
public class CategoryManagerController {
    
    private final CategoryManagerService categoryManagerService;
    
    public CategoryManagerController(CategoryManagerService categoryManagerService) {
        this.categoryManagerService = categoryManagerService;
    }
    
    /**
     * DB에 있는 모든 카테고리 조회
     * @return 카테고리 정보가 담긴 list
     */
    @GetMapping
    public List<CategoryData> getAllCategories() {
        return categoryManagerService.getAllCategories();
    }
    
    /**
     * enum으로 된 카테고리를 DB에 업데이트
     * @see Category
     */
    @PutMapping
    public void updateCategories() {
        categoryManagerService.updateCategories();
    }
    
    /**
     * url에 주어진 카테고리 id로 카테고리에 해당하고 영업중인 가게들을 조회 (휴무인 가게는 제외)
     * @see mybatis/mapper/CategoryMapper.xml $findShopsByCategoryId
     * @param id 카테고리 id
     * @param now 조회하는 시점의 시간 (영업중인 가게만 조회하기 위해 필요함)
     * @return 카테고리에 해당되는 가게의 정보 list
     */
    @GetMapping("/{id}/shops")
    public List<SearchedShopData> getShopsByCategory(
            @PathVariable("id") Long id, LocalDateTime now) {
        SearchShopByCategoryParam param = new SearchShopByCategoryParam(id, now);
        return categoryManagerService.getShopsByCategory(param);
    }
    
}
