package com.delivery.shop.shop;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.delivery.shop.category.CategoryManagerService;
import com.delivery.shop.closingday.CyclicRegularClosing;
import com.delivery.shop.closingday.LegalHolidayClosing;

@ExtendWith(MockitoExtension.class)
class ShopUpdateServiceTest {
    
    @Mock
    private ShopRepository shopRepository;
    @Mock
    private CategoryManagerService categoryManagerService;
    @InjectMocks
    ShopUpdateService shopUpdateService;
    Shop shop;
    
    @BeforeEach
    void setup() {
        shop = new Shop();
        when(shopRepository.findShopById(1L)).thenReturn(shop);
        shopUpdateService = new ShopUpdateService(shopRepository, categoryManagerService);
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
