package com.delivery.shop.shop;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.shop.businesshour.BusinessHour;
import com.delivery.shop.businesshour.BusinessHourConditions;
import com.delivery.shop.businesshour.BusinessHourPolicy;
import com.delivery.shop.businesshour.UpdateBusinessHoursDto;
import com.delivery.shop.category.Category;

@Service
public class ShopUpdateService {
    
    private final ShopRepository shopRepository;
    
    public ShopUpdateService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }
    
    @Transactional
    public List<BusinessHour> updateBusinessHour(Long id, UpdateBusinessHoursDto dto) {
        Shop shop = shopRepository.findShopById(id);
        BusinessHourPolicy policy = BusinessHourConditions.makeBusinessHoursBy(shop.getId(), dto);
        shop.updateBusinessHour(policy);
        shopRepository.updateBusinessHour(shop);
        return policy.getBusinessHours();
    }
    
    public void editIntroduce(Long id, String introduce) {
        Shop shop = shopRepository.findShopById(id);
        shop.editIntroduce(introduce);
        shopRepository.updateIntroduce(shop);
    }
    
    public void editPhoneNumber(Long id, String phone) {
        Shop shop = shopRepository.findShopById(id);
        shop.editPhoneNumber(phone);
        shopRepository.updatePhone(shop);
    }
    
    public void editDeliveryAreaGuide(Long id, String guide) {
        Shop shop = shopRepository.findShopById(id);
        shop.editDeliveryAreaGuide(guide);
        shopRepository.updateDeliveryAreaGuide(shop);
    }
    
    public void rename(Long id, String name) {
        Shop shop = shopRepository.findShopById(id);
        shop.rename(name);
        shopRepository.updateName(shop);
    }
    
    @Transactional
    public void updateCategory(Long id, UpdateCategoryRequest dto) {
        Shop shop = shopRepository.findShopById(id);
        List<Category> categories = Category.from(dto.getTypeNames());
        shop.updateCategory(categories);
        shopRepository.updateCategory(shop);
    }
}
