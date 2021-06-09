package com.delivery.shop.shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.delivery.shop.category.Category;

@Mapper
public interface ShopMapper {
    
    Shop findShopById(Long id);
    
    void updateIntroduce(Long id, String introduce);
    
    void updatePhone(Long id, String phone);
    
    void updateDeliveryAreaGuide(Long id, String guide);
    
    void updateName(Long id, String name);
    
    void updateCategory(Long shopId, List<Category> categories);
    
    void deleteCategory(Long shopId);
}
