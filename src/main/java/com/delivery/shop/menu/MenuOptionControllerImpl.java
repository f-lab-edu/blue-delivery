package com.delivery.shop.menu;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.response.HttpResponse;

@RestController
public class MenuOptionControllerImpl implements MenuOptionController {

    private MenuOptionService service;

    public MenuOptionControllerImpl(MenuOptionServiceImpl service) {
        this.service = service;
    }

    /**
     * 메뉴 옵션 그룹 추가
     *
     * @param menuId 옵션 그룹을 생성할 메뉴의 Id
     * @param dto 생성할 옵션 그룹
     *
     */
    @Override
    @PostMapping("/{menuId}/option-groups")
    public ResponseEntity<HttpResponse<?>> registerMenuOptionGroup(@PathVariable Long menuId,
                                                                   @RequestBody @Valid MenuOptionGroupDto dto) {
        dto.setMenuId(menuId);
        service.registerMenuOptionGroup(dto);
        return ResponseEntity.status(HttpStatus.OK).body(HttpResponse.response(dto));
    }

}
