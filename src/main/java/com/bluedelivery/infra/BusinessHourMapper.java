package com.bluedelivery.infra;

import org.apache.ibatis.annotations.Mapper;

import com.bluedelivery.domain.shop.BusinessHour;

@Mapper
public interface BusinessHourMapper {
    
    void insert(BusinessHour param);
    
    void deleteAllByShopId(Long shopId);
    
}
