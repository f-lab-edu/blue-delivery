package com.delivery.shop.category;

import java.util.List;

public interface CategoryManagerService {
    
    /**
     * 카테고리 조회
     *
     * @return Category 객체 리스트
     */
    List<Category> getAllCategories();
    
    /**
     * 카테고리 추가
     *
     * @param param 추가될 카테고리 정보
     */
    void addCategory(CreateCategoryParam param);
    
    /**
     * 카테고리 삭제
     *
     * @param id 삭제될 카테고리의 id
     */
    void deleteCategory(Long id);
    
    /**
     * 카테고리 정보 변경
     * @param param 카테고리 변경 정보
     */
    void editCategory(EditCategoryParam param);
    
}
