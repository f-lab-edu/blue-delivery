package com.bluedelivery.shop.category;

import java.util.List;

import com.bluedelivery.shop.search.SearchedShopData;

public class GetShopsByCategoryResponse {
    
    private List<SearchedShopData> shopsByCategory;
    
    public GetShopsByCategoryResponse(List<SearchedShopData> shopsByCategory) {
        this.shopsByCategory = shopsByCategory;
    }
    
    public List<SearchedShopData> getShopsByCategory() {
        return shopsByCategory;
    }
}
