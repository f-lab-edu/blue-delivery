package com.delivery.restaurant;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.delivery.restaurant.businesshour.BusinessHour;
import com.delivery.restaurant.businesshour.BusinessHourMapper;
import com.delivery.restaurant.businesshour.BusinessHours;

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
        BusinessHours bhs = restaurant.getBusinessHour();
        Set<BusinessHour> businessHoursByDayType = bhs.getBusinessHoursByDayType();
        
        restaurantMapper.save(restaurant.getName());
        for (BusinessHour businessHour : businessHoursByDayType) {
            businessHourMapper.insert(restaurant.getId(), businessHour.getOpen(), businessHour.getClose(),
                    businessHour.getDayType(), bhs.getBusinessHourType());
        }
    }
    
    @Override
    public void update(Restaurant restaurant) {
        BusinessHours bhs = restaurant.getBusinessHour();
        Set<BusinessHour> businessHoursByDayType = bhs.getBusinessHoursByDayType();
        
        restaurantMapper.update(restaurant.getId(), restaurant.getName());
        businessHourMapper.deleteAllByRestaurantId(restaurant.getId());
        for (BusinessHour businessHour : businessHoursByDayType) {
            businessHourMapper.insert(restaurant.getId(), businessHour.getOpen(), businessHour.getClose(),
                    businessHour.getDayType(), bhs.getBusinessHourType());
        }
    }
}
