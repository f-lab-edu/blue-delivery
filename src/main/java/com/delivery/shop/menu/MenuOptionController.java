package com.delivery.shop.menu;

import org.springframework.http.ResponseEntity;

import com.delivery.response.HttpResponse;

public interface MenuOptionController {

    ResponseEntity<HttpResponse<?>> registerMenuOptionGroup(Long menuId, MenuOptionGroupDto dto);
}
