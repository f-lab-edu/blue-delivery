package com.delivery.shop.menu;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.exception.NotFoundIdException;

@RestController
public class MenuGroupController {

    private MenuGroupService service;

    public MenuGroupController(MenuGroupService service) {
        this.service = service;
    }

    @PostMapping("shops/{shopId}/menuGroups")
    public ResponseEntity<MenuGroup> registerMenuGroup(@Validated @RequestBody MenuGroupDto dto,
                                                       @PathVariable Long shopId) {
        if (!dto.checkShopId(shopId)) {
            throw new NotFoundIdException("not found shopId");
        }
        service.registerMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("shops/{shopId}/menuGroups")
    public ResponseEntity<List<MenuGroupDto>> getMenuGroups(@PathVariable Long shopId) {

        List<MenuGroupDto> menuGroups = service.getMenuGroup(shopId);

        if (menuGroups == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<List<MenuGroupDto>>(menuGroups, HttpStatus.OK);
    }

}
