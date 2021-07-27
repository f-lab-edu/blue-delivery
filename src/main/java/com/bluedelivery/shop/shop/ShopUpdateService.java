package com.bluedelivery.shop.shop;

import static com.bluedelivery.common.response.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.shop.businesshour.BusinessHourConditions;
import com.bluedelivery.shop.businesshour.BusinessHourPolicy;
import com.bluedelivery.shop.businesshour.UpdateBusinessHoursDto;
import com.bluedelivery.shop.category.CategoryManagerService;
import com.bluedelivery.shop.closingday.LegalHolidayClosing;
import com.bluedelivery.shop.suspension.Suspension;

@Service
@Transactional
public class ShopUpdateService {
    
    private final ShopRepository shopRepository;
    private final CategoryManagerService categoryManagerServiceImpl;
    
    public ShopUpdateService(ShopRepository shopRepository, CategoryManagerService categoryManagerServiceImpl) {
        this.shopRepository = shopRepository;
        this.categoryManagerServiceImpl = categoryManagerServiceImpl;
    }
    
    public void updateBusinessHour(Long id, UpdateBusinessHoursDto dto) {
        Shop shop = getShop(id);
        BusinessHourPolicy policy = BusinessHourConditions.makeBusinessHoursBy(shop.getId(), dto);
        shop.updateBusinessHour(policy);
        shopRepository.updateBusinessHours(shop);
    }
    
    public void editIntroduce(Long id, String introduce) {
        Shop shop = getShop(id);
        shop.editIntroduce(introduce);
        shopRepository.updateIntroduce(shop);
    }
    
    public void editPhoneNumber(Long id, String phone) {
        Shop shop = getShop(id);
        shop.editPhoneNumber(phone);
        shopRepository.updatePhone(shop);
    }
    
    public void editDeliveryAreaGuide(Long id, String guide) {
        Shop shop = getShop(id);
        shop.editDeliveryAreaGuide(guide);
        shopRepository.updateDeliveryAreaGuide(shop);
    }
    
    public void rename(Long id, String name) {
        Shop shop = getShop(id);
        shop.rename(name);
        shopRepository.updateName(shop);
    }
    
    public void updateCategory(Long id, UpdateCategoryRequest dto) {
        Shop shop = getShop(id);
        shop.getCategories().updateAll(categoryManagerServiceImpl.getCategoriesById(dto.getCategoryIds()));
    }
    
    public void updateClosingDays(Long id, UpdateClosingDaysRequest closingDays) {
        Boolean closingOnLegalHolidays = closingDays.getLegalHolidays();
        List<TemporaryClosingParam> temporaries = closingDays.getTemporaryClosing();
        List<RegularClosingParam> regulars = closingDays.getRegularClosing();
        Shop shop = getShop(id);
        
        if (closingOnLegalHolidays) {
            shop.addClosingDayPolicy(new LegalHolidayClosing());
        }
        temporaries.stream().forEach(
                temporary -> shop.addClosingDayPolicy(temporary.toEntity()));
        regulars.stream().forEach(
                regular -> shop.addClosingDayPolicy(regular.toEntity()));
        shopRepository.deleteClosingDays(shop);
        shopRepository.updateClosingDays(shop);
    }
    
    public void expose(Long shopId, Boolean status) {
        Shop shop = getShop(shopId);
        shop.updateExposeStatus(status);
        shopRepository.updateExposeStatus(shop);
    }
    
    public void suspend(Long shopId, Suspension suspension) {
        Shop shop = getShop(shopId);
        shop.suspend(suspension);
        shopRepository.updateSuspension(shop);
    }
    
    private Shop getShop(Long id) {
        Shop shop = shopRepository.findShopById(id);
        if (shop == null) {
            throw new ApiException(SHOP_DOES_NOT_EXIST);
        }
        return shop;
    }
}
