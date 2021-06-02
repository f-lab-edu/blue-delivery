package com.delivery.restaurant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Transactional
    public void updateBusinessHour(Long id, UpdateBusinessHoursDto dto) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant does not exist"));
        restaurant.updateBusinessHour(
                businessHourConditions.makeBusinessHoursBy(dto.getType(), dto.getBusinessHours()));
        restaurantRepository.updateBusinessHour(restaurant);
    }
    
    public void editIntroduce(Long id, String introduce) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant does not exist"));
        restaurant.editIntroduce(introduce);
        restaurantRepository.updateIntroduce(restaurant);
    }
    
    public void editPhoneNumber(Long id, String phone) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant does not exist"));
        restaurant.editPhoneNumber(phone);
        restaurantRepository.updatePhone(restaurant);
    }
    
    public void editDeliveryAreaGuide(Long id, String guide) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant does not exist"));
        restaurant.editDeliveryAreaGuide(guide);
        restaurantRepository.updateDeliveryAreaGuide(restaurant);
    }
}
