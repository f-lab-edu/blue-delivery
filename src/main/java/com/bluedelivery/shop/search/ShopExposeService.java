package com.bluedelivery.shop.search;

import java.util.List;

import com.bluedelivery.shop.shop.Shop;

public interface ShopExposeService {
    List<Shop> getShopsByCategory(SearchShopByCategoryParam param);
}
