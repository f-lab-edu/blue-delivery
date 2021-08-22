package com.bluedelivery.application.shop;

import static com.bluedelivery.common.response.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.api.shop.UpdateCategoryRequest;
import com.bluedelivery.api.shop.UpdateClosingDaysRequest;
import com.bluedelivery.application.category.CategoryManagerService;
import com.bluedelivery.application.shop.businesshour.DayOfWeekMapper;
import com.bluedelivery.application.shop.dto.BusinessHoursTarget;
import com.bluedelivery.application.shop.dto.UpdateDeliveryAreaTarget;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.closingday.LegalHolidayClosing;
import com.bluedelivery.domain.closingday.Suspension;
import com.bluedelivery.domain.shop.BusinessHour;
import com.bluedelivery.domain.shop.DeliveryArea;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ShopUpdateService {
    
    private final ShopRepository shopRepository;
    private final CategoryManagerService categoryManagerService;
    private final AddressMapper addressMapper;
    
    public List<BusinessHour> updateBusinessHour(BusinessHoursTarget target) {
        Shop shop = getShop(target.getShopId());
        shop.updateBusinessHours(DayOfWeekMapper.map(target));
        return shop.getBusinessHours();
    }
    
    public void editIntroduce(Long id, String introduce) {
        Shop shop = getShop(id);
        shop.editIntroduce(introduce);
    }
    
    public void editPhoneNumber(Long id, String phone) {
        Shop shop = getShop(id);
        shop.editPhoneNumber(phone);
    }
    
    public void editDeliveryAreaGuide(Long id, String guide) {
        Shop shop = getShop(id);
        shop.editDeliveryAreaGuide(guide);
    }
    
    public void rename(Long id, String name) {
        Shop shop = getShop(id);
        shop.rename(name);
    }
    
    public void updateCategory(Long shopId, UpdateCategoryRequest dto) {
        Shop shop = getShop(shopId);
        shop.updateCategoryIds(categoryManagerService.getCategoriesById(dto.getCategoryIds()));
    }
    
    public void updateClosingDays(Long id, UpdateClosingDaysRequest closingDays) {
        Boolean closingOnLegalHolidays = closingDays.getLegalHolidays();
        List<TemporaryClosingParam> temporaries = closingDays.getTemporaryClosing();
        List<RegularClosingParam> regulars = closingDays.getRegularClosing();
        Shop shop = getShop(id);
        
        if (closingOnLegalHolidays) {
            shop.addClosingDayPolicy(new LegalHolidayClosing());
        }
        temporaries.forEach(temporary -> shop.addClosingDayPolicy( temporary.toEntity()));
        regulars.forEach(regular -> shop.addClosingDayPolicy(regular.toEntity()));
    }
    
    public void expose(Long shopId, Boolean status) {
        Shop shop = getShop(shopId);
        shop.updateExposeStatus(status);
    }
    
    public void suspend(Long shopId, Suspension suspension) {
        Shop shop = getShop(shopId);
        shop.addClosingDayPolicy(suspension);
    }
    
    @Transactional
    public List<DeliveryArea> updateDeliveryArea(UpdateDeliveryAreaTarget target) {
        Shop shop = getShop(target.getShopId());
        List<DeliveryArea> areas = addressMapper.deliveryAreas(target.getTownCodes());
        shop.updateDeliveryArea(areas);
        return areas;
    }
    
    private Shop getShop(Long id) {
        return shopRepository.findById(id).orElseThrow(() -> new ApiException(SHOP_DOES_NOT_EXIST));
    }
}
