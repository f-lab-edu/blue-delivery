package com.delivery.shop.shop;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.shop.businesshour.UpdateBusinessHoursDto;

@RestController
@RequestMapping("/shops")
public class ShopUpdateController {
    
    private ShopUpdateService updateService;
    
    public ShopUpdateController(ShopUpdateService updateService) {
        this.updateService = updateService;
    }
    
    @PutMapping("/{id}/business-hours")
    public void updateBusinessHours(
            @PathVariable("id") Long id, @RequestBody @Valid UpdateBusinessHoursDto dto) {
        updateService.updateBusinessHour(id, dto);
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
    
    @PutMapping("/{id}/categories")
    public void updateCategory(@PathVariable("id") Long shopId,
                               @RequestBody @Valid UpdateCategoryRequest dto) {
        updateService.updateCategory(shopId, dto);
    }
}
