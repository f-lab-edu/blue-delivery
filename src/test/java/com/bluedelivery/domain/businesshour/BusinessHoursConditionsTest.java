package com.bluedelivery.domain.businesshour;

import static com.bluedelivery.api.shop.dto.BusinessHourDay.*;
import static com.bluedelivery.api.shop.dto.BusinessHourDay.EVERY_DAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;

class BusinessHoursConditionsTest {
    
    private DayOfWeekMapper resolver = new DayOfWeekMapper();
    
    @Test
    @DisplayName("타입 EVERYDAY만 갖는 BusinessHour를 넘겨주면 모든 요일이 동일한 영업시간을 생성한다.")
    void everydayBusinessHourTest() {
        LocalTime open = LocalTime.of(9, 0);
        LocalTime close = LocalTime.of(20, 0);
        Map<BusinessHourDay, BusinessHourParam> hours = new LinkedHashMap<>();
        hours.put(EVERY_DAY, new BusinessHourParam(open, close));
    
    
        List<BusinessHour> resolve = resolver.map(BusinessHourType.EVERY_SAME_TIME, hours).entrySet().stream()
                .map(entry -> entry.getValue().toEntity(entry.getKey()))
                .collect(Collectors.toList());
    
        BusinessHours businessHours = new BusinessHours(resolve);
    
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
        Map<BusinessHourDay, BusinessHourParam> hours = new LinkedHashMap<>();
        hours.put(WEEKDAY, new BusinessHourParam(weekdayOpen, weekdayClose));
        hours.put(SATURDAY, new BusinessHourParam(satOpen, satClose));
        hours.put(SUNDAY, new BusinessHourParam(sunOpen, sunClose));
    
    
        List<BusinessHour> resolve = resolver.map(BusinessHourType.WEEKDAY_SAT_SUNDAY, hours).entrySet().stream()
                .map(entry -> entry.getValue().toEntity(entry.getKey()))
                .collect(Collectors.toList());
        BusinessHours businessHours = new BusinessHours(resolve);
        
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
        Map<BusinessHourDay, BusinessHourParam> hours = new LinkedHashMap<>();
        hours.put(WEEKDAY, new BusinessHourParam(everydayOpen, everydayClose));
        hours.put(SATURDAY, new BusinessHourParam(satOpen, satClose));
        hours.put(SUNDAY, new BusinessHourParam(sunOpen, sunClose));
        
        assertThrows(IllegalArgumentException.class, () -> resolver.map(BusinessHourType.EVERY_SAME_TIME, hours));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {13, 14, 15, 21, 23, 0, 1, 2, 10, 11, 12})
    @DisplayName("영업 종료 시간이 전일 오후 1시 ~ 새벽 3시까지인 경우, 현재 시간이 그 안에 포함되면 테스트가 성공한다.")
    void beforeAndAfterMidnight(int hour) {
        LocalTime now = LocalTime.of(hour, 00).plusNanos(1L);
        BusinessHour businessHour = new BusinessHour(DayOfWeek.FRIDAY,
                LocalTime.of(13, 0), LocalTime.of(03, 0));
        
        if ((13 <= hour && hour <= 23) || (0 <= hour && hour <= 3)) {
            assertThat(businessHour.isOpening(now)).isTrue();
        } else {
            assertThat(businessHour.isOpening(now)).isFalse();
        }
    }
    
}
