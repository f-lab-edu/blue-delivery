package com.delivery.shop.shop;

import org.springframework.stereotype.Repository;

import com.delivery.shop.businesshour.BusinessHour;
import com.delivery.shop.businesshour.BusinessHourMapper;
import com.delivery.shop.businesshour.BusinessHourResponse;

@Repository
public class ShopRepositoryMybatis implements ShopRepository {
    
    private final ShopMapper shopMapper;
    private final BusinessHourMapper businessHourMapper;
    
    public ShopRepositoryMybatis(ShopMapper shopMapper, BusinessHourMapper businessHourMapper) {
        this.shopMapper = shopMapper;
        this.businessHourMapper = businessHourMapper;
    }
    
    @Override
    public Shop findShopById(Long id) {
        Shop shop = shopMapper.findShopById(id);
        if (shop == null) {
            throw new IllegalArgumentException("shop does not exist");
        }
        return shop;
    }
    
    @Override
    public void updateBusinessHour(Shop shop) {
        businessHourMapper.deleteAllByShopId(shop.getId());
        insertBusinessHour(shop);
    }
    
    @Override
    public void updateIntroduce(Shop shop) {
        shopMapper.updateIntroduce(shop.getId(), shop.getIntroduce());
    }
    
    @Override
    public void updatePhone(Shop shop) {
        shopMapper.updatePhone(shop.getId(), shop.getPhone());
    }
    
    @Override
    public void updateDeliveryAreaGuide(Shop shop) {
        shopMapper.updateDeliveryAreaGuide(shop.getId(), shop.getDeliveryAreaGuide());
    }
    
    @Override
    public void updateName(Shop shop) {
        shopMapper.updateName(shop.getId(), shop.getName());
    }
    
    private BusinessHourResponse insertBusinessHour(Shop shop) {
        BusinessHourResponse bhResponse = shop.getBusinessHour();
        for (BusinessHour bh : bhResponse.getBusinessHours()) {
            businessHourMapper.insert(
                    new BusinessHour(
                            shop.getId(),
                            bh.getOpen(),
                            bh.getClose(),
                            bh.getDayOfWeek())
            );
        }
        return bhResponse;
    }
    
}
