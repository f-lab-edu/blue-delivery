package com.delivery.restaurant;

import java.util.Optional;
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
    public Optional<Restaurant> findRestaurantById(Long id) {
        return Optional.ofNullable(restaurantMapper.findRestaurantById(id));
    }
    
    @Override
    public void updateBusinessHour(Restaurant restaurant) {
        businessHourMapper.deleteAllByRestaurantId(restaurant.getId());
        insertBusinessHour(restaurant);
    }
    
    @Override
    public void updateIntroduce(Restaurant restaurant) {
        restaurantMapper.updateIntroduce(restaurant.getId(), restaurant.getIntroduce());
    }
    
    @Override
    public void updatePhone(Restaurant restaurant) {
        restaurantMapper.updatePhone(restaurant.getId(), restaurant.getPhone());
    }
    
    @Override
    public void updateDeliveryAreaGuide(Restaurant restaurant) {
        restaurantMapper.updateDeliveryAreaGuide(restaurant.getId(), restaurant.getDeliveryAreaGuide());
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
