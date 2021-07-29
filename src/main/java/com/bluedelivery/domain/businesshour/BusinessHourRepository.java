package com.bluedelivery.domain.businesshour;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluedelivery.domain.shop.Shop;

public interface BusinessHourRepository extends JpaRepository<BusinessHour, Long> {
    
    List<BusinessHour> findAllByShop(Shop shop);
}
