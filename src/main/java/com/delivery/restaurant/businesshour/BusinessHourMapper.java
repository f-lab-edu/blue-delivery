package com.delivery.restaurant.businesshour;

import java.time.LocalTime;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.delivery.utility.BusinessHourType;
import com.delivery.utility.DayType;

@Mapper
public interface BusinessHourMapper {
    
    @Insert("INSERT INTO BUSINESS_HOURS (REST_ID, OPEN, CLOSE, DAY_TYPE, BUSINESS_HOUR_TYPE) "
            + "VALUES (#{restId}, #{open}, #{close}, #{dayType}, #{businessHourType})")
    void insert(@Param("restId") Long restId, @Param("open") LocalTime open, @Param("close") LocalTime close,
                @Param("dayType") DayType dayType, @Param("businessHourType") BusinessHourType businessHourType);
    
    @Delete("DELETE FROM BUSINESS_HOURS WHERE REST_ID=#{restId}")
    void deleteAllByRestaurantId(@Param("restId") Long restId);
    
}
