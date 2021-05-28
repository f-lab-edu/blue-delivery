package com.delivery.restaurant;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.delivery.restaurant.businesshour.BusinessHour;
import com.delivery.restaurant.businesshour.BusinessHourMapper;
import com.delivery.restaurant.businesshour.BusinessHourPolicy;

@Repository
public class RestaurantRepositoryMybatis implements RestaurantRepository {
    
    private final RestaurantMapper restaurantMapper;
    private final BusinessHourMapper businessHourMapper;
    
    public RestaurantRepositoryMybatis(RestaurantMapper restaurantMapper, BusinessHourMapper businessHourMapper) {
        this.restaurantMapper = restaurantMapper;
        this.businessHourMapper = businessHourMapper;
    }
    
    @Override
    public Restaurant findRestaurantById(Long id) {
        return restaurantMapper.findRestaurantById(id);
    }
    
    @Override
    public void save(Restaurant restaurant) {
        restaurantMapper.save(restaurant.getName());
        insertBusinessHour(restaurant);
    }
    
    @Override
    public void update(Restaurant restaurant) {
        restaurantMapper.update(restaurant.getId(), restaurant.getName());
        businessHourMapper.deleteAllByRestaurantId(restaurant.getId());
        insertBusinessHour(restaurant);
    }
    
    private void insertBusinessHour(Restaurant restaurant) {
        BusinessHourPolicy bhs = restaurant.getBusinessHour();
        Set<BusinessHour> bhByType = bhs.getBusinessHoursByDayType();
        
        for (BusinessHour bh : bhByType) {
            businessHourMapper.insert(
                    restaurant.getId(),
                    bh.getOpen(),
                    bh.getClose(),
                    bh.getDayType(),
                    bhs.getBusinessHourType());
        }
    }
}
