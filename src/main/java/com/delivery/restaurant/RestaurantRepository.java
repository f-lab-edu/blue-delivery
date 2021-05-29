package com.delivery.restaurant;

import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {
    Restaurant findRestaurantById(Long id);
    
    void updateBusinessHour(Restaurant restaurant);
}
