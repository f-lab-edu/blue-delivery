package com.bluedelivery.api.shop;

import org.springframework.http.ResponseEntity;

import com.bluedelivery.common.response.HttpResponse;

public interface MenuOptionController {

    ResponseEntity<HttpResponse<?>> registerMenuOptionGroup(Long menuId, MenuOptionGroupDto dto);

    ResponseEntity<HttpResponse<?>> registerMenuOption(Long optionGroupId, MenuOptionDto menuOptionDto);
}
