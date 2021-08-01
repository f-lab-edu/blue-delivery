package com.bluedelivery.domain.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bluedelivery.domain.shop.ShopCategory;

@Repository
public interface ShopCategoryRepository extends JpaRepository<ShopCategory, Long> {
    @Modifying
    @Query("delete from ShopCategory sc where sc.shop.id = :shopId")
    void deleteByShopId(Long shopId);
}
