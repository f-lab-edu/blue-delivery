package com.bluedelivery.application.shop;

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
import com.bluedelivery.domain.businesshour.BusinessHourRepository;
import com.bluedelivery.domain.closingday.CyclicRegularClosing;
import com.bluedelivery.domain.closingday.LegalHolidayClosing;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopRepository;

@ExtendWith(MockitoExtension.class)
class ShopUpdateServiceTest {
    
    ShopUpdateService shopUpdateService;
    Shop shop;
    
    @BeforeEach
    void setup(@Mock ShopRepository shopRepository,
               @Mock CategoryManagerService categoryManagerService,
               @Mock BusinessHourRepository businessHourRepository) {
        shop = new Shop();
        when(shopRepository.findById(1L)).thenReturn(Optional.of(shop));
        shopUpdateService = new ShopUpdateService(shopRepository, categoryManagerService, businessHourRepository);
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
        shopUpdateService.updateClosingDays(1L, request);
        
        // 법정공휴일 테스트
        LegalHolidayClosing.getYearOf(Year.of(2021)).stream().forEach(
                x -> assertThat(shop.isClosingAt(x)).isTrue()
        );
        // 정기휴무일 테스트
        assertThat(shop.isClosingAt(lastMondayOnJune)).isTrue();
        assertThat(shop.isClosingAt(sunday)).isTrue();
        // 임시휴무일 테스트
        june18.datesUntil(june23.plusDays(1)).forEach(
                x -> assertThat(shop.isClosingAt(x)).isTrue()
        );
    }
    
}
