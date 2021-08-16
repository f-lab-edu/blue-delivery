package com.bluedelivery.api.menu;

import org.springframework.http.ResponseEntity;

import com.bluedelivery.common.response.HttpResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "메뉴 그룹")
public interface MenuGroupController {

    @ApiOperation(value = "메뉴 그룹 생성")
    ResponseEntity<HttpResponse> registerMenuGroup(Long shopId, RegisterMenuGroupDto dto);

    @ApiOperation(value = "메뉴 그룹 수정")
    ResponseEntity<HttpResponse> updateGroups(Long shopId, Long id, UpdateMenuGroupDto dto);

    @ApiOperation(value = "메뉴 그룹 삭제")
    ResponseEntity<HttpResponse> deleteGroups(Long shopId, Long id);
}
