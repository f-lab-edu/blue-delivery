package com.bluedelivery.api.menu.adapter;

import static com.bluedelivery.common.response.HttpResponse.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.api.menu.RegisterMenuGroupDto;
import com.bluedelivery.api.menu.UpdateMenuGroupDto;
import com.bluedelivery.application.shop.adapter.MenuGroupServiceImpl;
import com.bluedelivery.common.response.HttpResponse;


@RestController
@RequestMapping("/shops")
public class MenuGroupController {

    private MenuGroupServiceImpl service;

    public MenuGroupController(MenuGroupServiceImpl service) {
        this.service = service;
    }

    /**
     * 메뉴 그룹 추가
     *
     * @param shopId 메뉴 그룹을 추가할 shop의 ID
     * @param dto 생성할 메뉴 그룹 정보
     *
     */
    @PostMapping("/{shopId}/menu-groups")
    public ResponseEntity<HttpResponse> registerMenuGroup(@PathVariable Long shopId,
                                                          @Validated @RequestBody RegisterMenuGroupDto dto) {
        dto.setShopId(shopId);
        service.registerMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response(dto));
    }

    /**
     * 메뉴 그룹 수정
     *
     * @param shopId 해당 shopId의 메뉴 그룹
     * @param id 수정할 메뉴 그룹 ID
     * @param dto 수정할 메뉴 그룹 정보(이름, 설명)
     *
     */
    @PutMapping("/{shopId}/menu-groups/{id}")
    public ResponseEntity<HttpResponse> updateGroups(@PathVariable Long shopId,
                                                     @PathVariable Long id,
                                                     @Validated @RequestBody UpdateMenuGroupDto dto) {
        dto.setShopId(shopId);
        dto.setId(id);
        service.updateMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response(dto));
    }

    /**
     * 메뉴 그룹 삭제
     *
     * @param shopId 해당 shopId의 메뉴 그룹
     * @param id 삭제할 메뉴 그룹
     *
     */
    @DeleteMapping("/{shopId}/menu-groups/{id}")
    public ResponseEntity<HttpResponse> deleteGroups(@PathVariable Long shopId,
                                                     @PathVariable Long id) {
        service.deleteMenuGroup(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpResponse.response(id));
    }

}
