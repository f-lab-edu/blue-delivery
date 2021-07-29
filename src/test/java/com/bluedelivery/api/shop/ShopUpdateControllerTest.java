package com.bluedelivery.api.shop;

import static com.bluedelivery.api.shop.dto.BusinessHourDay.EVERY_DAY;
import static com.bluedelivery.domain.businesshour.BusinessHourType.EVERY_SAME_TIME;
import static java.time.DayOfWeek.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bluedelivery.api.shop.adapter.ShopUpdateControllerImpl;
import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.api.shop.dto.BusinessHoursRequest;
import com.bluedelivery.application.category.CategoryManagerService;
import com.bluedelivery.application.shop.RegularClosingParam;
import com.bluedelivery.application.shop.ShopUpdateService;
import com.bluedelivery.application.shop.TemporaryClosingParam;
import com.bluedelivery.application.shop.dto.BusinessHourParam;
import com.bluedelivery.common.config.GlobalExceptionHandler;
import com.bluedelivery.domain.businesshour.BusinessHour;
import com.bluedelivery.domain.businesshour.BusinessHourRepository;
import com.bluedelivery.domain.businesshour.BusinessHours;
import com.bluedelivery.domain.closingday.CyclicRegularClosing;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)
class ShopUpdateControllerTest {
    
    private MockMvc mvc;
    private ShopUpdateController controller;
    private ShopUpdateService service;
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setup(@Mock ShopRepository shopRepository,
               @Mock CategoryManagerService categoryManagerService,
               @Mock BusinessHourRepository businessHourRepository) {
        Shop shop = new Shop();
        when(shopRepository.findById(1L)).thenReturn(Optional.of(shop));
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        service = new ShopUpdateService(shopRepository, categoryManagerService, businessHourRepository);
        controller = new ShopUpdateControllerImpl(service);
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .alwaysDo(print())
                .build();
    }
    
    @Test
    void updateBusinessHourTest() throws Exception {
        //given
        Map<BusinessHourDay, BusinessHourParam> hours = new LinkedHashMap<>();
        LocalTime open = LocalTime.of(9, 0);
        LocalTime close = LocalTime.MIDNIGHT;
        hours.put(EVERY_DAY, new BusinessHourParam(open, close));
        BusinessHoursRequest dto = new BusinessHoursRequest(EVERY_SAME_TIME, hours);
        
        //when
        MockHttpServletResponse response = mvc.perform(put("/shops/{id}/business-hours", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(dto)))
                .andReturn().getResponse();
        
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).contains(
                objectMapper.writeValueAsString(new BusinessHours(List.of(
                        new BusinessHour(MONDAY, open, close),
                        new BusinessHour(TUESDAY, open, close),
                        new BusinessHour(WEDNESDAY, open, close),
                        new BusinessHour(THURSDAY, open, close),
                        new BusinessHour(FRIDAY, open, close),
                        new BusinessHour(SATURDAY, open, close),
                        new BusinessHour(SUNDAY, open, close)
                ))));
    }
    
    @Test
    @Disabled
    void updateClosingDays() throws Exception {
        LocalDate june18 = LocalDate.of(2021, Month.JUNE, 18);
        LocalDate june23 = june18.plusDays(5);
        
        UpdateClosingDaysRequest request = new UpdateClosingDaysRequest(
                true,
                List.of(new RegularClosingParam(DayOfWeek.SUNDAY),
                        new RegularClosingParam(CyclicRegularClosing.Cycle.LAST, MONDAY)),
                List.of(new TemporaryClosingParam(june18, june23))
        );
        
        mvc.perform(put("/shops/1/closing-days")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
