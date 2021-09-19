package com.bluedelivery.api.shop;

import io.swagger.annotations.ApiOperation;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bluedelivery.api.category.GetShopsByCategoryResponse;
import com.bluedelivery.common.response.HttpResponse;
import com.bluedelivery.domain.menu.MenuGroup;
import com.bluedelivery.domain.shop.Shop;

public interface ShopExposeController {
    
    /**
     * url에 주어진 카테고리 id로 카테고리에 해당하고 영업중인 가게들을 조회 (휴무인 가게는 제외)
     *
     * @param id 카테고리 id
     * @return 카테고리에 해당되는 가게의 정보 list
     * @see mybatis/mapper/CategoryMapper.xml $findShopsByCategoryId
     */
    @GetMapping("/categories/{categoryId}/shops")
    ResponseEntity<GetShopsByCategoryResponse> getShopsByCategory(@PathVariable("categoryId") Long id);

    @GetMapping("/shops/ranking")
    ResponseEntity<HttpResponse<List<Shop>>> getTotalOrdersTop();

    @ApiOperation(value = "해당 가게의 모든 메뉴 불러오기")
    @GetMapping("/menus/{shopId}")
    ResponseEntity<List<MenuGroup>> getAllMenusByShopId(@PathVariable Long shopId);
}
