package com.delivery.shop;

import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository {
    Shop findShopById(Long id);
    
    void updateBusinessHour(Shop shop);
    
    void updateIntroduce(Shop shop);
    
    void updatePhone(Shop shop);
    
    void updateDeliveryAreaGuide(Shop shop);
    
    void updateName(Shop shop);
}
