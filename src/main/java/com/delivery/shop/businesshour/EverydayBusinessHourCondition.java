package com.delivery.shop.businesshour;

import static com.delivery.shop.businesshour.UpdateBusinessHoursDto.*;

import java.time.DayOfWeek;

public class EverydayBusinessHourCondition implements BusinessHourCondition {
    @Override
    public boolean isSatisfied(BusinessHourType calendarType, BusinessHourRequestParams params) {
        if (params.isEveryDayHours(calendarType)) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHourPolicy returnBusinessHourPolicy(Long shopId, BusinessHourRequestParams params) {
        BusinessHourPolicy policy = new BusinessHourPolicy();
        BusinessHourRequestParam bh = params.retreiveParamByDayType(DayType.EVERYDAY);
        for (DayOfWeek day : DayOfWeek.values()) {
            policy.update(bh.toEntity(shopId, day));
        }
        return policy;
    }
    
}
