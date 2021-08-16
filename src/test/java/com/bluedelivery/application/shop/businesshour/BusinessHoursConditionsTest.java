package com.bluedelivery.application.shop.businesshour;

import static com.bluedelivery.api.shop.dto.BusinessHourDay.*;
import static com.bluedelivery.api.shop.dto.BusinessHourDay.EVERY_DAY;
import static com.bluedelivery.application.shop.businesshour.BusinessHourType.EVERY_SAME_TIME;
import static com.bluedelivery.application.shop.businesshour.BusinessHourType.WEEKDAY_SAT_SUNDAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;
import com.bluedelivery.application.shop.dto.BusinessHoursTarget;
import com.bluedelivery.domain.shop.BusinessHour;

class BusinessHoursConditionsTest {
    
    private static final LocalDate SOME_FRIDAY = LocalDate.of(2021, Month.AUGUST, 13);
    private DayOfWeekMapper mapper = new DayOfWeekMapper();
    
    @Test
    @DisplayName("타입 EVERYDAY만 갖는 BusinessHour를 넘겨주면 모든 요일이 동일한 영업시간을 생성한다.")
    void everydayBusinessHourTest() {
        //given
        LocalTime open = LocalTime.of(9, 0);
        LocalTime close = LocalTime.of(20, 0);
        Map<BusinessHourDay, BusinessHourParam> hours = new LinkedHashMap<>();
        hours.put(EVERY_DAY, new BusinessHourParam(open, close));
        BusinessHoursTarget target = new BusinessHoursTarget(1L, EVERY_SAME_TIME, hours);
        
        //when
        List<BusinessHour> mapped = DayOfWeekMapper.map(target);
        
        //then
        for (BusinessHour each : mapped) {
            assertThat(each.getOpen()).isEqualTo(open);
            assertThat(each.getClose()).isEqualTo(close);
        }
    }
    
    @Test
    @DisplayName("타입 WEEKDAY, SATURDAY, SUNDAY만 넘겨주면 평일, 토요일, 일요일별 영업 시간을 생성한다.")
    void weekdayWeekendBusinessHourTest() {
        //given
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
        BusinessHoursTarget target = new BusinessHoursTarget(1L, WEEKDAY_SAT_SUNDAY, hours);
    
        //when
        List<BusinessHour> mapped = DayOfWeekMapper.map(target);
    
        //then
        for (BusinessHour each : mapped) {
            if (each.getDayOfWeek() == (DayOfWeek.SATURDAY)) {
                assertThat(each.getOpen()).isEqualTo(satOpen);
                assertThat(each.getClose()).isEqualTo(satClose);
            } else if (each.getDayOfWeek() == (DayOfWeek.SUNDAY)) {
                assertThat(each.getOpen()).isEqualTo(sunOpen);
                assertThat(each.getClose()).isEqualTo(sunClose);
            } else {
                assertThat(each.getOpen()).isEqualTo(weekdayOpen);
                assertThat(each.getClose()).isEqualTo(weekdayClose);
            }
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
        BusinessHoursTarget target = new BusinessHoursTarget(1L, EVERY_SAME_TIME, hours);
        
        assertThrows(IllegalArgumentException.class, () -> mapper.map(target));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {13, 14, 15, 21, 23, 0, 1, 2, 10, 11, 12})
    @DisplayName("영업 종료 시간이 전일 오후 1시 ~ 새벽 3시까지인 경우, 현재 시간이 그 안에 포함되면 테스트가 성공한다.")
    void beforeAndAfterMidnight(int hour) {
        LocalDateTime now = LocalDateTime.of(SOME_FRIDAY, LocalTime.of(hour, 1));
        BusinessHour businessHour = new BusinessHour(DayOfWeek.THURSDAY,
                LocalTime.of(13, 0), LocalTime.of(03, 0));
        
        if ((13 <= hour && hour <= 23) || (0 <= hour && hour <= 3)) {
            assertThat(businessHour.isOpen(now)).isTrue();
        } else {
            assertThat(businessHour.isOpen(now)).isFalse();
        }
    }
}
