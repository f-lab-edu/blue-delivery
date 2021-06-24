package com.delivery.shop.category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 모든 카테고리 조회
     *
     * @return 카테고리 정보가 담긴 list
     */
    @GetMapping
    public ResponseEntity<GetAllCategoriesResponse> getAllCategories() {
        GetAllCategoriesResponse body = new GetAllCategoriesResponse(
                categoryManagerService.getAllCategories().stream()
                        .map(Category::toResponse)
                        .collect(Collectors.toList())
        );
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
    /**
     * url에 주어진 카테고리 id로 카테고리에 해당하고 영업중인 가게들을 조회 (휴무인 가게는 제외)
     *
     * @param id 카테고리 id
     * @return 카테고리에 해당되는 가게의 정보 list
     * @see mybatis/mapper/CategoryMapper.xml $findShopsByCategoryId
     */
    @GetMapping("/{id}/shops")
    public ResponseEntity<GetShopsByCategoryResponse> getShopsByCategory(@PathVariable("id") Long id) {
        LocalDateTime when = LocalDateTime.now();
        List<SearchedShopData> shops =
                categoryManagerService.getShopsByCategory(new SearchShopByCategoryParam(id, when)).stream()
                        .map(shop -> new SearchedShopData(shop.getId(), shop.getName(), shop.isOpeningAt(when)))
                        .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetShopsByCategoryResponse(shops));
    }
    
}
