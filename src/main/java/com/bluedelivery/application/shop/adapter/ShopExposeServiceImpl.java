package com.bluedelivery.application.shop.adapter;

import static java.lang.Boolean.compare;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bluedelivery.application.shop.SearchShopByCategoryParam;
import com.bluedelivery.application.shop.ShopExposeService;
import com.bluedelivery.domain.category.CategoryRepository;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopExposeServiceImpl implements ShopExposeService {

    private final ShopRepository shopRepository;
    
    // TODO '요청 클라이언트의 위치'에 맞는 카테고리-가게 리스트를 받아오는 캐시서비스를 따로 만들고
    // TODO ShopExposeService에서는 여러가지 조건(영업, 정렬방법 등)을 만족하는 데이터를 내려주도록
    public List<Shop> getShopsByCategory(SearchShopByCategoryParam param) {
        LocalDateTime when = param.getNow();
        return shopRepository.findShopsByCategoryId(param).stream()
                .filter(shop -> !shop.isClosingAt(when.toLocalDate())) // 휴무가 아닌 가게만 선택
                .sorted((o1, o2) -> compare(o2.isOpeningAt(when), o1.isOpeningAt(when))) // 영업중 가게(true) 순 정렬
                .collect(toList());
    }
}
