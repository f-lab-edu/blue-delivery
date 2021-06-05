package com.delivery.restaurant;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
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
            @PathVariable("id") Long id, @RequestBody @Valid UpdateBusinessHoursDto dto) {
        updateService.updateBusinessHour(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    
    @PatchMapping("/{id}/introduce")
    public void editIntroduce(@PathVariable("id") Long id, @RequestBody String introduce) {
        updateService.editIntroduce(id, introduce);
    }
    
    @PatchMapping("/{id}/phone")
    public void editPhoneNumber(@PathVariable("id") Long id,
                                @RequestBody @Valid EditPhoneRequest dto) {
        updateService.editPhoneNumber(id, dto.getPhone());
    }
    
    @PatchMapping("/{id}/delivery-area-guide")
    public void editDeliveryAreaGuid(@PathVariable("id") Long id, @RequestBody String guide) {
        updateService.editDeliveryAreaGuide(id, guide);
    }
    
    @PatchMapping("/{id}/name")
    public void rename(@PathVariable("id") Long id, @RequestBody String name) {
        updateService.rename(id, name);
    }
}
