package com.delivery.shop.businesshour;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessHourMapper {
    
    void insert(BusinessHour param);
    
    void deleteAllByShopId(Long shopId);
    
}
