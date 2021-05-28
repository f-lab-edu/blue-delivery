package com.delivery.restaurant;

import java.util.List;

import com.delivery.utility.BusinessHourType;


public interface BusinessHourCondition {
    
    boolean isSatisfied(BusinessHourType type, List<BusinessHour> bh);
    
    BusinessHours matchBusinessHours(List<BusinessHour> bh);
}
