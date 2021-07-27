package com.bluedelivery.shop.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.application.category.CategoryNotFoundException;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.common.response.ErrorCode;
import com.bluedelivery.common.response.HttpResponse;
import com.bluedelivery.shop.businesshour.UpdateBusinessHoursDto;
import com.bluedelivery.shop.suspension.SuspensionRequest;

@RestController
public class ShopUpdateControllerImpl implements ShopUpdateController {
    
    private ShopUpdateService updateService;
    
    public ShopUpdateControllerImpl(ShopUpdateService updateService) {
        this.updateService = updateService;
    }
    
    public void updateBusinessHours(
            Long id, UpdateBusinessHoursDto dto) {
        updateService.updateBusinessHour(id, dto);
    }
    
    public void editIntroduce(Long id, String introduce) {
        updateService.editIntroduce(id, introduce);
    }
    
    public void editPhoneNumber(Long id, EditPhoneRequest dto) {
        updateService.editPhoneNumber(id, dto.getPhone());
    }
    
    public void editDeliveryAreaGuid(Long id, String guide) {
        updateService.editDeliveryAreaGuide(id, guide);
    }
    
    public void rename(Long id, String name) {
        updateService.rename(id, name);
    }
    
    public ResponseEntity<HttpResponse<?>> updateCategory(Long shopId, UpdateCategoryRequest dto) {
        try {
            updateService.updateCategory(shopId, dto);
            return ResponseEntity.ok(HttpResponse.response("successfully updated"));
        } catch (CategoryNotFoundException ex) {
            throw new ApiException(ErrorCode.CATEGORY_NOT_FOUND);
        }
    }
    
    public void updateClosingDays(Long shopId, UpdateClosingDaysRequest closingDays) {
        updateService.updateClosingDays(shopId, closingDays);
    }
    
    public void setExpose(Long shopId, Boolean expose) {
        updateService.expose(shopId, expose);
    }

    public void suspendShop(Long shopId, SuspensionRequest suspension) {
        updateService.suspend(shopId, suspension.toEntity());
    }
    
}
