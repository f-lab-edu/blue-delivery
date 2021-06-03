package com.delivery.restaurant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.restaurant.businesshour.BusinessHourConditions;
import com.delivery.restaurant.businesshour.UpdateBusinessHoursDto;

@Service
public class RestaurantUpdateService {
    
    private final RestaurantRepository restaurantRepository;
    
    public RestaurantUpdateService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    
    @Transactional
    public void updateBusinessHour(Long id, UpdateBusinessHoursDto dto) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        restaurant.updateBusinessHour(
                BusinessHourConditions.makeBusinessHoursBy(dto.getBusinessHourType(), dto.getBusinessHours()));
        restaurantRepository.updateBusinessHour(restaurant);
    }
    
    public void editIntroduce(Long id, String introduce) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        restaurant.editIntroduce(introduce);
        restaurantRepository.updateIntroduce(restaurant);
    }
    
    public void editPhoneNumber(Long id, String phone) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        restaurant.editPhoneNumber(phone);
        restaurantRepository.updatePhone(restaurant);
    }
    
    public void editDeliveryAreaGuide(Long id, String guide) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        restaurant.editDeliveryAreaGuide(guide);
        restaurantRepository.updateDeliveryAreaGuide(restaurant);
    }
    
    public void rename(Long id, String name) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        restaurant.rename(name);
        restaurantRepository.updateName(restaurant);
    }
}
