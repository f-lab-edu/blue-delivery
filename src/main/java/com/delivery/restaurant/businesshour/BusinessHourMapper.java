package com.delivery.restaurant.businesshour;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessHourMapper {
    
    void insert(Long restId, LocalTime open, LocalTime close, DayOfWeek dayOfWeek);
    
    void deleteAllByRestaurantId(Long restId);
    
}
