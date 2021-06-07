package com.delivery.restaurant;

import org.springframework.stereotype.Repository;

import com.delivery.restaurant.businesshour.BusinessHour;
import com.delivery.restaurant.businesshour.BusinessHourMapper;
import com.delivery.restaurant.businesshour.BusinessHourResponse;

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
        Restaurant restaurant = restaurantMapper.findRestaurantById(id);
        if (restaurant == null) {
            throw new IllegalArgumentException("restaurant does not exist");
        }
        return restaurant;
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
    
    @Override
    public void updateName(Restaurant restaurant) {
        restaurantMapper.updateName(restaurant.getId(), restaurant.getName());
    }
    
    private BusinessHourResponse insertBusinessHour(Restaurant restaurant) {
        BusinessHourResponse bhResponse = restaurant.getBusinessHour();
        for (BusinessHour bh : bhResponse.getBusinessHours()) {
            businessHourMapper.insert(
                    new BusinessHour(
                            restaurant.getId(),
                            bh.getOpen(),
                            bh.getClose(),
                            bh.getDayOfWeek())
            );
        }
        return bhResponse;
    }
    
}
