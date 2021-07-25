package com.delivery.shop.category;

import static com.delivery.shop.category.CreateCategoryParam.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.delivery.response.HttpResponse;
import com.delivery.shop.category.EditCategoryParam.EditCategoryRequest;

@RequestMapping("/categories")
public interface CategoryManagerController {
    
    /**
     * 모든 카테고리 조회
     *
     * @return 카테고리 정보가 담긴 list
     */
    @GetMapping
    ResponseEntity<HttpResponse<List<Categories>>> getAllCategories();
    
    /**
     * 카테고리 추가
     *
     * @param dto 추가할 카테고리 정보
     * @return 성공시 응답 200 OK
     */
    @PostMapping
    ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest dto);
    
    /**
     * 카테고리 삭제
     *
     * @param id 삭제할 카테고리의 id
     * @return 성공시 응답 204 NO_CONTENT
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable("id") Long id);
    
    /**
     * 카테고리 수정
     *
     * @param id 수정할 카테고리의 id
     * @param request 카테고리 수정 정보
     * @return 성공시 응답 200 OK
     */
    @PatchMapping("/{id}")
    ResponseEntity<?> editCategory(@PathVariable("id") Long id, @RequestBody EditCategoryRequest request);
}

