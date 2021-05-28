package com.delivery.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.delivery.utility.BusinessHourType;
import com.delivery.utility.DayType;

class BusinessHoursConditionsTest {
    
    BusinessHoursConditions policies = new BusinessHoursConditions();
    List<BusinessHour> bh = new ArrayList<>();
    
    
    @Test
    @DisplayName("타입 EVERYDAY만 갖는 BusinessHour를 넘겨주면 모든 요일이 동일한 영업시간을 생성한다.")
    void everydayBusinessHourTest() {
        LocalTime open = LocalTime.of(9, 0);
        LocalTime close = LocalTime.of(20, 0);
        bh.add(new BusinessHour(open, close));
        
        BusinessHours businessHours = policies.makeBusinessHoursBy(BusinessHourType.EVERYDAY, bh);
        for (DayOfWeek day : DayOfWeek.values()) {
            BusinessHour hour = businessHours.getBusinessHourOf(day);
            assertThat(hour.getOpen()).isEqualTo(open);
            assertThat(hour.getClose()).isEqualTo(close);
            System.out.println(hour + " on " + day);
        }
    }
    
    @Test
    @DisplayName("타입 WEEKDAY, SATURDAY, SUNDAY만 넘겨주면 평일, 토요일, 일요일별 영업 시간을 생성한다.")
    void weekdayWeekendBusinessHourTest() {
        LocalTime weekdayOpen = LocalTime.of(9, 0);
        LocalTime weekdayClose = LocalTime.of(20, 0);
        LocalTime satOpen = LocalTime.of(11, 0);
        LocalTime satClose = LocalTime.of(21, 0);
        LocalTime sunOpen = LocalTime.of(19, 0);
        LocalTime sunClose = LocalTime.of(23, 58);
        bh.add(new BusinessHour(weekdayOpen, weekdayClose, DayType.WEEKDAY));
        bh.add(new BusinessHour(satOpen, satClose, DayType.SATURDAY));
        bh.add(new BusinessHour(sunOpen, sunClose, DayType.SUNDAY));
        
        BusinessHours businessHours = policies.makeBusinessHoursBy(BusinessHourType.WEEKDAY_SAT_SUNDAY, bh);
        for (DayOfWeek day : DayOfWeek.values()) {
            BusinessHour hour = businessHours.getBusinessHourOf(day);
            if (day.compareTo(DayOfWeek.FRIDAY) <= 0) {
                assertThat(hour.getOpen()).isEqualTo(weekdayOpen);
                assertThat(hour.getClose()).isEqualTo(weekdayClose);
            } else if (day.equals(DayOfWeek.SATURDAY)) {
                assertThat(hour.getOpen()).isEqualTo(satOpen);
                assertThat(hour.getClose()).isEqualTo(satClose);
            } else {
                assertThat(hour.getOpen()).isEqualTo(sunOpen);
                assertThat(hour.getClose()).isEqualTo(sunClose);
            }
            System.out.println(hour + " on " + day);
        }
    }
    
    @Test
    void mixedTypeFailTest() {
        LocalTime everydayOpen = LocalTime.of(9, 0);
        LocalTime everydayClose = LocalTime.of(20, 0);
        LocalTime satOpen = LocalTime.of(11, 0);
        LocalTime satClose = LocalTime.of(21, 0);
        LocalTime sunOpen = LocalTime.of(19, 0);
        LocalTime sunClose = LocalTime.of(23, 58);
        bh.add(new BusinessHour(everydayOpen, everydayClose));
        bh.add(new BusinessHour(satOpen, satClose, DayType.SATURDAY));
        bh.add(new BusinessHour(sunOpen, sunClose, DayType.SUNDAY));
    
        assertThrows(IllegalArgumentException.class, () -> policies.makeBusinessHoursBy(BusinessHourType.EVERYDAY, bh));
    }
    
}
