package com.delivery.shop.category;

import static com.delivery.shop.category.CreateCategoryParam.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.delivery.shop.category.EditCategoryParam.EditCategoryRequest;

public interface CategoryManagerController {
    
    /**
     * 모든 카테고리 조회
     *
     * @return 카테고리 정보가 담긴 list
     */
    ResponseEntity<GetAllCategoriesResponse> getAllCategories();
    
    /**
     * url에 주어진 카테고리 id로 카테고리에 해당하고 영업중인 가게들을 조회 (휴무인 가게는 제외)
     *
     * @param id 카테고리 id
     * @return 카테고리에 해당되는 가게의 정보 list
     * @see mybatis/mapper/CategoryMapper.xml $findShopsByCategoryId
     */
    ResponseEntity<GetShopsByCategoryResponse> getShopsByCategory(@PathVariable("id") Long id);
    
    /**
     * 카테고리 추가
     *
     * @param dto 추가할 카테고리 정보
     * @return 성공시 응답 200 OK
     */
    ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest dto);
    
    /**
     * 카테고리 삭제
     *
     * @param id
     * @return 성공시 응답 204 NO_CONTENT
     */
    ResponseEntity<?> deleteCategory(@PathVariable("id") Long id);
    
    /**
     * 카테고리 수정
     *
     * @param id
     * @param request
     * @return 성공시 응답 200 OK
     */
    @PatchMapping("/{id}")
    ResponseEntity<?> editCategory(@PathVariable("id") Long id, @RequestBody EditCategoryRequest request);
}

