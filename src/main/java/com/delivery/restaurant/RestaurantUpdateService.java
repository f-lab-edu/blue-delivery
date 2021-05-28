package com.delivery.restaurant;

import org.springframework.stereotype.Service;

import com.delivery.restaurant.businesshour.BusinessHourConditions;
import com.delivery.restaurant.businesshour.UpdateBusinessHoursDto;

@Service
public class RestaurantUpdateService {
    
    private final RestaurantRepository restaurantRepository;
    private final BusinessHourConditions businessHourConditions;
    
    public RestaurantUpdateService(RestaurantRepository restaurantRepository,
                                   BusinessHourConditions businessHourConditions) {
        this.restaurantRepository = restaurantRepository;
        this.businessHourConditions = businessHourConditions;
    }
    
    public void updateBusinessHours(Long id, UpdateBusinessHoursDto dto) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        restaurant.updateBusinessHour(
                businessHourConditions.makeBusinessHoursBy(dto.getType(), dto.getBusinessHours()));
        restaurantRepository.update(restaurant);
    }
    
}
