package com.bluedelivery.api.shop.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.api.category.GetShopsByCategoryResponse;
import com.bluedelivery.api.shop.SearchedShopData;
import com.bluedelivery.api.shop.ShopExposeController;
import com.bluedelivery.application.shop.ShopExposeService;
import com.bluedelivery.domain.shop.Shop;

@RestController
public class ShopExposeHttpController implements ShopExposeController {
    
    private final ShopExposeService shopExposeService;
    
    public ShopExposeHttpController(ShopExposeService shopExposeService) {
        this.shopExposeService = shopExposeService;
    }
    
    @GetMapping("categories/{id}/shops")
    public ResponseEntity<GetShopsByCategoryResponse> getShopsByCategory(@PathVariable("id") Long id) {
        List<Shop> shops = shopExposeService.getShopsByCategory(id);
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetShopsByCategoryResponse(shops.stream()
                        // Shop List -> DTO List
                        .map(shop -> new SearchedShopData(
                                shop.getId(),
                                shop.getName(),
                                shop.isOpeningAt()))
                        .collect(Collectors.toList()))
                );
    }
}
