package com.delivery.shop.menu;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     *
     * @param menuGroupId 메뉴 그룹 ID
     * @param dto 추가할 메뉴 정보
     * @return
     */
    @PostMapping("/{menuGroupId}/menu")
    public ResponseEntity<MenuDto> registerMenu(@PathVariable Long menuGroupId,
                                       @RequestBody @Valid MenuDto dto) {
        if (menuGroupId != dto.getMenuGroupId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        menuService.registerMenu(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
