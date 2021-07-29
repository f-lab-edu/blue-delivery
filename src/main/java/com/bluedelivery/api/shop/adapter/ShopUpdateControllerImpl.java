package com.bluedelivery.api.shop.adapter;

import static com.bluedelivery.common.response.HttpResponse.FAIL;
import static com.bluedelivery.common.response.HttpResponse.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.api.shop.EditPhoneRequest;
import com.bluedelivery.api.shop.ShopUpdateController;
import com.bluedelivery.api.shop.SuspensionRequest;
import com.bluedelivery.api.shop.UpdateCategoryRequest;
import com.bluedelivery.api.shop.UpdateClosingDaysRequest;
import com.bluedelivery.api.shop.dto.BusinessHoursRequest;
import com.bluedelivery.application.category.CategoryNotFoundException;
import com.bluedelivery.application.shop.ShopUpdateService;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.common.response.ErrorCode;
import com.bluedelivery.common.response.HttpResponse;
import com.bluedelivery.domain.businesshour.BusinessHours;

@RestController
public class ShopUpdateControllerImpl implements ShopUpdateController {
    
    private ShopUpdateService updateService;
    
    public ShopUpdateControllerImpl(ShopUpdateService updateService) {
        this.updateService = updateService;
    }
    
    public ResponseEntity<HttpResponse<BusinessHours>> updateBusinessHours(Long shopId, BusinessHoursRequest dto) {
        try {
            BusinessHours businessHours = updateService.updateBusinessHour(dto.toTarget(shopId));
            return ResponseEntity.ok(response(businessHours));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response(FAIL, ex.getMessage()));
        }
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
            return ResponseEntity.ok(response("successfully updated"));
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
