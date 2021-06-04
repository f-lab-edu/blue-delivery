package com.delivery.restaurant.businesshour;

import java.util.List;

import com.delivery.utility.BusinessHourType;


public interface BusinessHourCondition {
    
    boolean isSatisfied(BusinessHourType type, List<BusinessHour> bh);
    
    BusinessHourPolicy returnBusinessHourPolicy(List<BusinessHour> bh);
}
