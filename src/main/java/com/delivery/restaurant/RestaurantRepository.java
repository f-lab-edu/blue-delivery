package com.delivery.restaurant;

import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {
    Restaurant findRestaurantById(Long id);
    
    void save(Restaurant restaurant);
    
    void update(Restaurant restaurant);
}
