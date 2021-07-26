package com.bluedelivery.shop.category;

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
     *              return 추가된 엔티티
     */
    Category addCategory(CreateCategoryParam param);
    
    /**
     * 카테고리 삭제
     *
     * @param id 삭제될 카테고리의 id
     *           return 삭제된 카테고리 개수
     */
    long deleteCategory(Long id);
    
    /**
     * 카테고리 정보 변경
     *
     * @param param 카테고리 변경 정보
     */
    void editCategory(EditCategoryParam param);
    
    /**
     * 카테고리 목록을 id로 조회
     *
     * @param categoryIds 카테고리 id 목록
     * @return 카테고리 목록
     */
    List<Category> getCategoriesById(List<Long> categoryIds);
}
