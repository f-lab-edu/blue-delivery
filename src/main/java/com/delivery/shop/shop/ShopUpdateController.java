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
    
    /**
     * 해당 가게의 카테고리를 입력받은 대로 업데이트
     * @param shopId 가게 id
     * @param dto 추가할 카테고리 enum name을 담은 dto
     */
    @PutMapping("/{id}/categories")
    public void updateCategory(@PathVariable("id") Long shopId,
                               @RequestBody @Valid UpdateCategoryRequest dto) {
        updateService.updateCategory(shopId, dto);
    }
    
    /**
     * 해당 가게의 휴무일을 입력받고 업데이트한다.
     * @param shopId 가게 d
     * @param closingDays 휴무일 정보
     */
    @PutMapping("/{id}/closing-days")
    public void updateClosingDays(@PathVariable("id") Long shopId,
                                  @RequestBody @Valid UpdateClosingDaysRequest closingDays) {
        updateService.updateClosingDays(shopId, closingDays);
    }
}
