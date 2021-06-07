package com.delivery.shop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.shop.businesshour.BusinessHourConditions;
import com.delivery.shop.businesshour.BusinessHourResponse;
import com.delivery.shop.businesshour.UpdateBusinessHoursDto;

@Service
public class RestaurantUpdateService {
    
    private final ShopRepository shopRepository;
    
    public RestaurantUpdateService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }
    
    @Transactional
    public BusinessHourResponse updateBusinessHour(Long id, UpdateBusinessHoursDto dto) {
        Shop shop = shopRepository.findShopById(id);
        shop.updateBusinessHour(
                BusinessHourConditions.makeBusinessHoursBy(shop.getId(), dto));
        shopRepository.updateBusinessHour(shop);
        return shop.getBusinessHour();
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
}
