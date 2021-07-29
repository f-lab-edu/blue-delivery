package com.bluedelivery.domain.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluedelivery.domain.shop.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
