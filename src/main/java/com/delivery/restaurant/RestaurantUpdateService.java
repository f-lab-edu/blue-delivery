package com.delivery.restaurant;

import org.springframework.stereotype.Service;

@Service
public class RestaurantUpdateService {
    
    private final RestaurantRepository restaurantRepository;
    private final BusinessHoursConditions businessHoursConditions;
    
    public RestaurantUpdateService(RestaurantRepository restaurantRepository,
                                   BusinessHoursConditions businessHoursConditions) {
        this.restaurantRepository = restaurantRepository;
        this.businessHoursConditions = businessHoursConditions;
    }
    
    public void updateBusinessHours(Long id, UpdateBusinessHoursDto dto) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        restaurant.updateBusinessHour(
                businessHoursConditions.makeBusinessHoursBy(dto.getType(), dto.getBusinessHours()));
        restaurantRepository.save(restaurant);
    }
    
}
