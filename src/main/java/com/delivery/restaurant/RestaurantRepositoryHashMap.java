package com.delivery.restaurant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryHashMap implements RestaurantRepository {
    
    private final Map<Long, Restaurant> restaurantMap;
    private long idCount = 0;
    
    public RestaurantRepositoryHashMap() {
        this.restaurantMap = new HashMap<>();
    }
    
    @Override
    public Restaurant findRestaurantById(Long id) {
        if (restaurantMap.containsKey(id)) {
            return restaurantMap.get(id);
        }
        throw new IllegalArgumentException();
    }
    
    @Override
    public void save(Restaurant restaurant) {
        restaurantMap.put(idCount, restaurant);
    }
}
