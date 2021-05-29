package com.delivery.restaurant;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.restaurant.businesshour.UpdateBusinessHoursDto;

@RestController
@RequestMapping("/restaurants")
public class RestaurantUpdateController {
    
    private RestaurantUpdateService updateService;
    
    public RestaurantUpdateController(RestaurantUpdateService updateService) {
        this.updateService = updateService;
    }
    
    @PutMapping("/{id}/business-hours")
    public ResponseEntity<UpdateBusinessHoursDto> updateBusinessHours(
            @PathVariable("id") Long id, @Valid @RequestBody UpdateBusinessHoursDto dto) {
        updateService.updateBusinessHour(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
