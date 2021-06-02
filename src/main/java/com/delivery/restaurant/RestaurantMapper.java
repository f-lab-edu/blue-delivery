package com.delivery.restaurant;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RestaurantMapper {
    
    @Select("SELECT * FROM RESTAURANTS WHERE ID = #{id}")
    Restaurant findRestaurantById(@Param("id") Long id);
    
    void updateIntroduce(Long id, String introduce);
    
    void updatePhone(Long id, String phone);
    
    void updateDeliveryAreaGuide(Long id, String guide);
}
