package com.bluedelivery.domain.shop;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bluedelivery.application.shop.SearchShopByCategoryParam;
import com.bluedelivery.domain.shop.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    
    @Query("select s from Shop s join ShopCategory sc on sc.category.id = :#{#param.id}")
    List<Shop> findShopsByCategoryId(@Param("param") SearchShopByCategoryParam param);
    
}
