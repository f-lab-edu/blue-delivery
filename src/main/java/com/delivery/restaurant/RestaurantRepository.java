package com.delivery.restaurant;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {
    Optional<Restaurant> findRestaurantById(Long id);
    
    void updateBusinessHour(Restaurant restaurant);
    
    void updateIntroduce(Restaurant restaurant);
    
    void updatePhone(Restaurant restaurant);
    
    void updateDeliveryAreaGuide(Restaurant restaurant);
}
