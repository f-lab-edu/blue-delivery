package com.bluedelivery.infra.shop;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bluedelivery.application.shop.businesshour.BusinessHourCondition;
import com.bluedelivery.application.shop.businesshour.EverydayBusinessHourCondition;
import com.bluedelivery.application.shop.businesshour.WeekdayWeekendBusinessHourCondition;

@Configuration
public class ShopConfig {
    
    @Bean
    public List<BusinessHourCondition> businessHourConditions() {
        return List.of(
                new EverydayBusinessHourCondition(),
                new WeekdayWeekendBusinessHourCondition());
    }
}
