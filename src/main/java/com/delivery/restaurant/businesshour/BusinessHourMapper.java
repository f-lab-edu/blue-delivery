package com.delivery.restaurant.businesshour;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessHourMapper {
    
    void insert(BusinessHour param);
    
    void deleteAllByRestaurantId(Long restId);
    
}
