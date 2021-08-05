package com.bluedelivery.application.shop;

import java.util.List;

import com.bluedelivery.domain.shop.Shop;

public interface ShopExposeService {
    List<Shop> getShopsByCategory(Long categoryId);
}
