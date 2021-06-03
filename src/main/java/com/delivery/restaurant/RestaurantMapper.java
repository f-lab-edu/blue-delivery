package com.delivery.restaurant;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestaurantMapper {
    
    Restaurant findRestaurantById(Long id);
    
    void updateIntroduce(Long id, String introduce);
    
    void updatePhone(Long id, String phone);
    
    void updateDeliveryAreaGuide(Long id, String guide);
    
    void updateName(Long id, String name);
}
