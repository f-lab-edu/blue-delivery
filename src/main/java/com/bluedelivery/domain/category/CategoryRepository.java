package com.bluedelivery.domain.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bluedelivery.application.category.EditCategoryParam;
import com.bluedelivery.application.shop.SearchShopByCategoryParam;
import com.bluedelivery.domain.shop.Shop;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    long deleteCategoryById(Long id);
    
    List<Category> findCategoriesByIdIn(List<Long> categoryIds);
}
