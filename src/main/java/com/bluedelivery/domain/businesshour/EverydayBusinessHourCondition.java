package com.bluedelivery.domain.businesshour;

import static com.bluedelivery.api.shop.UpdateBusinessHoursDto.*;

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
    public BusinessHourPolicy returnBusinessHourPolicy(Long shopId, BusinessHourRequestParams params) {
        BusinessHourPolicy policy = new BusinessHourPolicy();
        BusinessHourRequestParam bh = params.retrieveParamByDayType(DayType.EVERYDAY);
        for (DayOfWeek day : DayOfWeek.values()) {
            policy.update(bh.toEntity(shopId, day));
        }
        return policy;
    }
    
}
