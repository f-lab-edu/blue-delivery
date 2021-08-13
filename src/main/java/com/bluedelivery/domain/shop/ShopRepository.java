package com.bluedelivery.domain.shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    
    @Query("select s from Shop s join Category c on c.id = :categoryId")
    List<Shop> findShopsByCategoryId(Long categoryId);
    
}
