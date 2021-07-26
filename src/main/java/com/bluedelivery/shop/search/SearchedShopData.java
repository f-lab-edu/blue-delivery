package com.bluedelivery.shop.search;

// TODO 나중에 대표메뉴 조회결과, 평점, 배달소요시간, 최소주문, 배달팁 정보 등등 추가되어야 함
public class SearchedShopData {
    private Long id;
    private String name;
    private boolean isOpening;
    
    public SearchedShopData(Long id, String name, boolean isOpening) {
        this.id = id;
        this.name = name;
        this.isOpening = isOpening;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isOpening() {
        return isOpening;
    }
}
