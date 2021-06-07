package com.delivery.restaurant.businesshour;

import static com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.*;

import java.time.DayOfWeek;

public class EverydayBusinessHourCondition implements BusinessHourCondition {
    @Override
    public boolean isSatisfied(BusinessHourType type, BusinessHourRequestParams params) {
        if (params.isEveryDayHours(type)) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHourPolicy returnBusinessHourPolicy(Long restId, BusinessHourRequestParams params) {
        BusinessHourPolicy policy = new BusinessHourPolicy();
        BusinessHourRequestParam bh = params.getParamByDayType(DayType.EVERYDAY);
        for (DayOfWeek day : DayOfWeek.values()) {
            policy.update(bh.toEntity(day));
        }
        return policy;
    }
    
}
