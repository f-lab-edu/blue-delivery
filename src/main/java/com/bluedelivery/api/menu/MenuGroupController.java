package com.bluedelivery.api.menu;

import org.springframework.http.ResponseEntity;

import com.bluedelivery.common.response.HttpResponse;

public interface MenuGroupController {

    ResponseEntity<HttpResponse> registerMenuGroup(Long shopId, RegisterMenuGroupDto dto);


    ResponseEntity<HttpResponse> updateGroups(Long shopId, Long id, UpdateMenuGroupDto dto);


    ResponseEntity<HttpResponse> deleteGroups(Long shopId, Long id);
}
