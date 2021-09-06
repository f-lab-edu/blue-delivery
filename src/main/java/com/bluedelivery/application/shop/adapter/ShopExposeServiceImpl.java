package com.bluedelivery.application.shop.adapter;

import static java.lang.Boolean.compare;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bluedelivery.application.shop.OrderRankingStrategy;
import com.bluedelivery.application.shop.ShopExposeService;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShopExposeServiceImpl implements ShopExposeService {

    private final ShopRepository shopRepository;

    private final OrderRankingStrategy orderRankingStrategy;
    
    // TODO '요청 클라이언트의 위치'에 맞는 카테고리-가게 리스트를 받아오는 캐시서비스를 따로 만들고
    // TODO ShopExposeService에서는 여러가지 조건(영업, 정렬방법 등)을 만족하는 데이터를 내려주도록
    public List<Shop> getShopsByCategory(Long categoryId) {
        return shopRepository.findShopsByCategoryId(categoryId).stream()
                .filter(shop -> !shop.isClosed(LocalDateTime.now())) // 휴무가 아닌 가게만 선택NotificationService
                .sorted((o1, o2) -> compare(o2.isOpen(), o1.isOpen())) // 영업중 가게(true) 순 정렬
                .collect(toList());
    }

    // TODO 일정 시간이 지나면 Redis의 이전 Ranking 데이터 삭제
    // TODO 카테고리별 랭킹
    public List<Shop> getTotalOrdersRanking() {
        return orderRankingStrategy.getShopRanking();
    }
}
