package com.bluedelivery.shop.menu;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.exception.ApiException;
import com.bluedelivery.response.ErrorCode;

@RestController
@RequestMapping("/shops")
public class MenuGroupController {

    private MenuGroupService service;

    public MenuGroupController(MenuGroupService service) {
        this.service = service;
    }

    /**
     * 메뉴 그룹을 추가
     *
     *
     * @param dto 가입할 메뉴 그룹 정보
     * @return
     */

    @PostMapping("/{shopId}/menu-groups")
    public ResponseEntity<MenuGroup> registerMenuGroup(@Validated @RequestBody MenuGroupDto dto,
                                                       @PathVariable Long shopId) {
        if (!dto.checkShopId(shopId)) {
            throw new ApiException(ErrorCode.SHOP_DOES_NOT_EXIST);
        }
        service.registerMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 메뉴 그룹 조회.
     *
     * @param shopId 매장 ID
     * @return
     */
    @GetMapping("/{shopId}/menu-groups")
    public ResponseEntity<List<MenuGroup>> getMenuGroups(@PathVariable Long shopId) {

        List<MenuGroup> menuGroups = service.getMenuGroup(shopId);

        if (menuGroups == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<List<MenuGroup>>(menuGroups, HttpStatus.OK);
    }

    /**
     * 메뉴 그룹 수정.
     *
     *
     * @param dto 수정할 메뉴 그룹
     * @return
     */
    @PutMapping("/{shopId}/menu-groups")
    public ResponseEntity<MenuGroupDto> updateGroups(@PathVariable Long shopId,
                                                     @RequestBody MenuGroupDto dto) {
        if (dto.getName() == null || dto.getName() == "") {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        service.updateMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 메뉴 그룹 삭제
     *
     *
     * @param menuGroupId 삭제할 메뉴 그룹
     * @return
     */
    @DeleteMapping("/{shopId}/menu-groups/{menuGroupId}")
    public ResponseEntity<MenuGroupDto> deleteGroups(@PathVariable Long shopId,
                                                     @PathVariable Long menuGroupId) {
        service.deleteMenuGroup(menuGroupId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
