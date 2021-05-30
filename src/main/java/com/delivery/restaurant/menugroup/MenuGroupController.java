package com.delivery.restaurant.menugroup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class MenuGroupController {

    private MenuGroupService service;

    public MenuGroupController(MenuGroupService service) {
        this.service = service;
    }

    @PostMapping("shops/{shopId}/menuGroups")
    public ResponseEntity<MenuGroup> registerMenuGroup(@Validated @RequestBody MenuGroupRegisterDto dto, @PathVariable Long shopId) {
        service.registerMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
