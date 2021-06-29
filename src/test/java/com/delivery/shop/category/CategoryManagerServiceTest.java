package com.delivery.shop.category;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.delivery.shop.businesshour.BusinessHour;
import com.delivery.shop.businesshour.BusinessHourPolicy;
import com.delivery.shop.closingday.ClosingDayPolicies;
import com.delivery.shop.closingday.WeeklyRegularClosing;
import com.delivery.shop.shop.Shop;

@ExtendWith(MockitoExtension.class)
class CategoryManagerServiceTest {
    
    CategoryManagerService categoryManagerService;
    LocalDateTime now = LocalDateTime.of(2021, Month.JUNE, 18, 14, 0);
    SearchShopByCategoryParam param = new SearchShopByCategoryParam(1L, now);
    
    // TODO 수정
    @Test
    @DisplayName("조회하는 시점은 18일 금요일이므로, 주어진 가게들 중 금요일 휴무인 가게는 조회되지 않는다. 그리고 영업중인 가게가 앞에온다.")
    void getShopsByCategory(@Mock CategoryRepository categoryRepository) {
        categoryManagerService = new CategoryManagerService(categoryRepository);
        ClosingDayPolicies closingFriday = new ClosingDayPolicies();
        closingFriday.addClosingDayPolicy(new WeeklyRegularClosing(DayOfWeek.FRIDAY));
        BusinessHour open1PM = new BusinessHour(DayOfWeek.FRIDAY, LocalTime.of(13, 0), LocalTime.of(23, 59));
        BusinessHour open3PM = new BusinessHour(DayOfWeek.FRIDAY, LocalTime.of(15, 0), LocalTime.of(23, 59));
        BusinessHourPolicy open1PmPolicy = new BusinessHourPolicy();
        BusinessHourPolicy open3PmPolicy = new BusinessHourPolicy();
        open1PmPolicy.update(open1PM);
        open3PmPolicy.update(open3PM);
    
        when(categoryRepository.findShopsByCategoryId(param)).thenReturn(
                List.of(new Shop(1L, "kyochon", open3PmPolicy, closingFriday),
                        new Shop(2L, "bbq", open1PmPolicy, new ClosingDayPolicies()),
                        new Shop(3L, "pooradak", open3PmPolicy, new ClosingDayPolicies())
                )
        );
        
        List<Shop> result = categoryManagerService.getShopsByCategory(param);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).isOpeningAt(this.now)).isEqualTo(true);
        assertThat(result.get(1).isOpeningAt(this.now)).isEqualTo(false);
    }
}
