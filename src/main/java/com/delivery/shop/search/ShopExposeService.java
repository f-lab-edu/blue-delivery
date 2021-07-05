package com.delivery.shop.search;

import java.util.List;

import com.delivery.shop.shop.Shop;

public interface ShopExposeService {
    List<Shop> getShopsByCategory(SearchShopByCategoryParam param);
}
