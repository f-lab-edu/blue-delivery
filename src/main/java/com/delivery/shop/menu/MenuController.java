package com.delivery.shop.menu;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu-groups")
public class MenuController {

    MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 메뉴 추가
     *
     * @param menuGroupId 메뉴 그룹 ID
     * @param dto 추가할 메뉴 정보
     * @return
     */
    @PostMapping("/{menuGroupId}/menus")
    public ResponseEntity<MenuDto> registerMenu(@PathVariable Long menuGroupId,
                                       @RequestBody @Valid MenuDto dto) {
        menuService.registerMenu(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 메뉴 상태 변경
     *
     * @param menuGroupId 메뉴 그룹 ID
     * @param dto 변경할 Status 정보
     *
     */
    // ToDo 메뉴 조회시에 메뉴 상태가 HIDDEN인 메뉴는 조회되지 않음
    @PatchMapping("/{menuGroupId}/menus")
    public ResponseEntity<MenuDto> menuStatusUpdate(@PathVariable Long menuGroupId, @RequestBody MenuDto dto) {
        menuService.menuStatusUpdate(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
