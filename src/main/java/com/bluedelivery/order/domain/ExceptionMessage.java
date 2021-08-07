package com.bluedelivery.order.domain;

public interface ExceptionMessage {
    String SHOP_IS_NOT_OPEN = "Shop is not open now";
    String ORDER_LIST_IS_EMPTY = "Ordered list is Empty";
    String ORDERED_AND_MENU_ARE_DIFFERENT = "There has been a change between ordered and different";
    String ORDERED_MENU_NOT_FOUND = "Menu you ordered does not exist";
    String ORDERED_AMOUNT_LOWER_THAN_MINIMUM_ORDER_AMOUNT = "Ordered amount is lower than minimum order amount";
    String ORDER_USER_DOES_NOT_EXIST = "User information does not exist";
}

