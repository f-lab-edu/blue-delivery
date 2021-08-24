package com.bluedelivery.api.menu;

import org.springframework.http.ResponseEntity;

import com.bluedelivery.common.response.HttpResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "메뉴")
public interface MenuController {

    @ApiOperation(value = "메뉴 생성")
    ResponseEntity<HttpResponse<?>> registerMenu(Long menuGroupId, RegisterMenuDto dto);

    @ApiOperation(value = "메인 메뉴 설정 및 해제")
    ResponseEntity<HttpResponse<?>> setMainMenu(Long menuGroupId, Long menuId);

    @ApiOperation(value = "메뉴 상태 변경")
    ResponseEntity<HttpResponse<?>> updateMenuStatus(Long menuGroupId, Long menuId, UpdateMenuDto dto);

    @ApiOperation(value = "메뉴 삭제")
    ResponseEntity<HttpResponse<?>> deleteMenu(Long menuGroupId, Long menuId);

}
