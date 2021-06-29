package com.delivery.shop.category;

import java.util.List;

import com.delivery.shop.search.SearchedShopData;

public class GetShopsByCategoryResponse {
    
    private List<SearchedShopData> shopsByCategory;
    
    public GetShopsByCategoryResponse(List<SearchedShopData> shopsByCategory) {
        this.shopsByCategory = shopsByCategory;
    }
    
    public List<SearchedShopData> getShopsByCategory() {
        return shopsByCategory;
    }
}
