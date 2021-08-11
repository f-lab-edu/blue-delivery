package com.bluedelivery.order.domain;

public interface ExceptionMessage {
    String SHOP_IS_NOT_OPEN = "영업중인 가게가 아닙니다.";
    String SHOP_DOES_NOT_EXIST = "가게가 존재하지 않습니다.";
    String ORDER_LIST_IS_EMPTY = "주문 목록이 비어있습니다.";
    String ORDERED_AND_MENU_ARE_DIFFERENT = "주문한 상품이 판매하고 있는 상품과 일치하지 않습니다. ";
    String ORDERED_AMOUNT_LOWER_THAN_MINIMUM_ORDER_AMOUNT = "주문 금액이 최소 주문 금액 미만입니다.";
    String ORDER_USER_DOES_NOT_EXIST = "로그인한 유저가 존재하지 않습니다.";
}

