package com.bluedelivery.domain.shop;

import org.springframework.stereotype.Repository;

import com.bluedelivery.domain.shop.Shop;

@Repository
public interface ShopRepository {
    Shop findShopById(Long id);
    
    void updateIntroduce(Shop shop);
    
    void updatePhone(Shop shop);
    
    void updateDeliveryAreaGuide(Shop shop);
    
    void updateName(Shop shop);
    
    void updateCategory(Shop shop);
    
    void deleteCategory(Shop shop);
    
    void updateBusinessHours(Shop shop);
    
    void deleteClosingDays(Shop shop);
    
    void updateClosingDays(Shop shop);
    
    void updateExposeStatus(Shop shop);
    
    void updateSuspension(Shop shop);
    
}
