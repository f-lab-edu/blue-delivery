package com.delivery.shop.category;

import static com.delivery.shop.category.CreateCategoryParam.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.shop.category.EditCategoryParam.EditCategoryRequest;
import com.delivery.shop.shop.SearchedShopData;
import com.delivery.shop.shop.Shop;

@RestController
@RequestMapping("/categories")
public class CategoryManagerHttpController implements CategoryManagerController {
    
    private final CategoryManagerService categoryManagerService;
    
    public CategoryManagerHttpController(CategoryManagerService categoryManagerService) {
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
        List<Shop> shops = categoryManagerService.getShopsByCategory(new SearchShopByCategoryRequest(id, when));
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetShopsByCategoryResponse(shops.stream()
                        .map(shop -> new SearchedShopData(
                                shop.getId(),
                                shop.getName(),
                                shop.isOpeningAt(when)))
                        .collect(Collectors.toList()))
                );
    }
    
    /**
     * 카테고리 추가
     *
     * @param dto 추가할 카테고리 정보
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest dto) {
        categoryManagerService.addCategory(dto.toParam());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    /**
     * 카테고리 삭제
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryManagerService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    /**
     * 카테고리 수정
     *
     * @param id
     * @param request
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> editCategory(@PathVariable("id") Long id, @RequestBody EditCategoryRequest request) {
        categoryManagerService.editCategory(request.toParam(id));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}
