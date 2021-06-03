package com.delivery.restaurant.businesshour;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BusinessHourMapper {
    
    @Insert("INSERT INTO BUSINESS_HOURS (REST_ID, OPEN, CLOSE, DAY_OF_WEEK) "
            + "VALUES (#{restId}, #{open}, #{close}, #{dayOfWeek})")
    void insert(@Param("restId") Long restId, @Param("open") LocalTime open, @Param("close") LocalTime close,
                @Param("dayOfWeek") DayOfWeek key);
    
    @Delete("DELETE FROM BUSINESS_HOURS WHERE REST_ID=#{restId}")
    void deleteAllByRestaurantId(@Param("restId") Long restId);
    
    List<Map<DayOfWeek, BusinessHour>> findHoursByRestaurantId(Long id);
}
