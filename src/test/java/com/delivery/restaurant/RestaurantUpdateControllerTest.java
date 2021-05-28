package com.delivery.restaurant;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.delivery.restaurant.businesshour.BusinessHour;
import com.delivery.restaurant.businesshour.UpdateBusinessHoursDto;
import com.delivery.utility.BusinessHourType;
import com.delivery.utility.DayType;
import com.fasterxml.jackson.databind.ObjectMapper;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class RestaurantUpdateControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @MockBean(name = "restaurantRepository")
    RestaurantRepository restaurantRepository;
    
    @Test
    void separatedDaysPassValidatorTest() throws Exception {
        long id = 1L;
        when(restaurantRepository.findRestaurantById(id)).thenReturn(new Restaurant());
        
        UpdateBusinessHoursDto dto = new UpdateBusinessHoursDto(
                BusinessHourType.WEEKDAY_SAT_SUNDAY,
                new BusinessHour(LocalTime.of(9, 0), LocalTime.of(10, 0), DayType.WEEKDAY),
                new BusinessHour(LocalTime.of(9, 0), LocalTime.of(10, 0), DayType.SATURDAY),
                new BusinessHour(LocalTime.of(11, 0), LocalTime.of(12, 0), DayType.SUNDAY)
        );
        String content = objectMapper.writeValueAsString(dto);
        
        mockMvc.perform(put("/restaurant/" + id + "/business-hours")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    void everydayPassValidatorTest() throws Exception {
        long id = 1L;
        when(restaurantRepository.findRestaurantById(id)).thenReturn(new Restaurant());
        
        UpdateBusinessHoursDto dto = new UpdateBusinessHoursDto(
                BusinessHourType.EVERYDAY,
                new BusinessHour(LocalTime.of(9, 0), LocalTime.of(10, 0))
        );
        String content = objectMapper.writeValueAsString(dto);
        
        mockMvc.perform(put("/restaurant/" + id + "/business-hours")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    void everydayWithAnotherFailValidationTest() throws Exception {
        long id = 1L;
        when(restaurantRepository.findRestaurantById(id)).thenReturn(new Restaurant());
        
        UpdateBusinessHoursDto dto = new UpdateBusinessHoursDto(
                BusinessHourType.EVERYDAY,
                new BusinessHour(LocalTime.of(9, 0), LocalTime.of(10, 0)),
                new BusinessHour(LocalTime.of(9, 0), LocalTime.of(10, 0), DayType.SUNDAY)
        );
        String content = objectMapper.writeValueAsString(dto);
        
        mockMvc.perform(put("/restaurant/" + id + "/business-hours")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void lackOfSeparatedDayFailValidationTest() throws Exception {
        long id = 1L;
        when(restaurantRepository.findRestaurantById(id)).thenReturn(new Restaurant());
        
        UpdateBusinessHoursDto dto = new UpdateBusinessHoursDto(
                BusinessHourType.WEEKDAY_SAT_SUNDAY,
                new BusinessHour(LocalTime.of(9, 0), LocalTime.of(10, 0), DayType.WEEKDAY),
                new BusinessHour(LocalTime.of(9, 0), LocalTime.of(10, 0), DayType.SUNDAY)
        );
        String content = objectMapper.writeValueAsString(dto);
        
        mockMvc.perform(put("/restaurant/" + id + "/business-hours")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
