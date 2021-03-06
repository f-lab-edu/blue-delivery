package com.bluedelivery.application.shop;

import static com.bluedelivery.domain.closingday.LocalDateTimeConverter.toLocalDateTime;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bluedelivery.api.shop.UpdateClosingDaysRequest;
import com.bluedelivery.application.category.CategoryManagerService;
import com.bluedelivery.application.shop.businesshour.DayOfWeekMapper;
import com.bluedelivery.application.shop.businesshour.EverydayBusinessHourCondition;
import com.bluedelivery.application.shop.businesshour.WeekdayWeekendBusinessHourCondition;
import com.bluedelivery.domain.closingday.CyclicRegularClosing;
import com.bluedelivery.domain.closingday.LegalHolidayClosing;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopRepository;

@ExtendWith(MockitoExtension.class)
class ShopUpdateServiceTest {
    
    ShopUpdateService service;
    Shop shop;
    private DayOfWeekMapper mapper = new DayOfWeekMapper(List.of(
            new EverydayBusinessHourCondition(),
            new WeekdayWeekendBusinessHourCondition()));
    
    @BeforeEach
    void setup(@Mock ShopRepository shopRepository,
               @Mock CategoryManagerService categoryManagerService,
               @Mock AddressMapper addressMapper) {
        shop = new Shop();
        when(shopRepository.findById(1L)).thenReturn(Optional.of(shop));
        service = new ShopUpdateService(shopRepository, categoryManagerService, addressMapper, mapper);
    }
    
    @Test
    void updateClosingDays() {
        LocalDate lastMondayOnJune = LocalDate.of(2021, Month.JUNE, 28);
        LocalDate sunday = LocalDate.of(2021, Month.JUNE, 20);
        LocalDate june18 = LocalDate.of(2021, Month.JUNE, 18);
        LocalDate june23 = june18.plusDays(5);
        
        UpdateClosingDaysRequest request = new UpdateClosingDaysRequest(
                true,
                List.of(new RegularClosingParam(DayOfWeek.SUNDAY),
                        new RegularClosingParam(CyclicRegularClosing.Cycle.LAST, DayOfWeek.MONDAY)),
                List.of(new TemporaryClosingParam(june18, june23))
        );
        service.updateClosingDays(1L, request);
        
        // ??????????????? ?????????
        LegalHolidayClosing.getHolidaysOf(Year.of(2021)).forEach(
                x -> assertThat(shop.isClosed(toLocalDateTime(x))).isTrue()
        );
        // ??????????????? ?????????
        assertThat(shop.isClosed(toLocalDateTime(lastMondayOnJune))).isTrue();
        assertThat(shop.isClosed(toLocalDateTime(sunday))).isTrue();
        // ??????????????? ?????????
        june18.datesUntil(june23.plusDays(1)).forEach(
                x -> assertThat(shop.isClosed(toLocalDateTime(x))).isTrue()
        );
    }
}
