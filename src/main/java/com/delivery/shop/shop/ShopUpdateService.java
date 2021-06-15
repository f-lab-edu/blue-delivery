package com.delivery.shop.shop;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.shop.businesshour.BusinessHourConditions;
import com.delivery.shop.businesshour.BusinessHourPolicy;
import com.delivery.shop.businesshour.UpdateBusinessHoursDto;
import com.delivery.shop.category.Category;
import com.delivery.shop.closingday.LegalHolidayClosing;

@Service
@Transactional
public class ShopUpdateService {
    
    private final ShopRepository shopRepository;
    
    public ShopUpdateService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
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
        List<Category> categories = Category.from(dto.getTypeNames());
        shop.updateCategory(categories);
        shopRepository.deleteCategory(shop);
        shopRepository.updateCategory(shop);
    }
    
    public void updateClosingDays(Long shopId, UpdateClosingDaysRequest closingDays) {
        Boolean closingOnLegalHolidays = closingDays.getLegalHolidays();
        List<TemporaryClosingParam> temporaries = closingDays.getTemporaryClosing();
        List<RegularClosingParam> regulars = closingDays.getRegularClosing();
        Shop shop = getShop(shopId);
        
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
    
    private Shop getShop(Long id) {
        Shop shop = shopRepository.findShopById(id);
        if (shop == null) {
            throw new IllegalArgumentException("shop does not exist");
        }
        return shop;
    }
}
