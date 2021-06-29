package com.delivery.shop.shop;

import static com.delivery.exception.ExceptionEnum.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.exception.ApiException;
import com.delivery.shop.businesshour.BusinessHourConditions;
import com.delivery.shop.businesshour.BusinessHourPolicy;
import com.delivery.shop.businesshour.UpdateBusinessHoursDto;
import com.delivery.shop.category.CategoryManagerService;
import com.delivery.shop.closingday.LegalHolidayClosing;
import com.delivery.shop.suspension.Suspension;

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
        List<Long> inputs = dto.getCategoryIds();
        shop.getCategories().updateAll(
                categoryManagerServiceImpl.getAllCategories().stream()
                .filter(category -> inputs.contains(category.getId())) // 전체 카테고리 중 입력받은 카테고리만 선택
                .collect(Collectors.toList())
        );
        shop.getCategories().toString();
        shopRepository.deleteCategory(shop);
        shopRepository.updateCategory(shop);
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
