package com.delivery.restaurant.menugroup;

import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuGroupController {

    private MenuGroupService service;

    public MenuGroupController(MenuGroupService service) {
        this.service = service;
    }


    @PostMapping("shops/{shopId}/menuGroups")
    public ResponseEntity<MenuGroup> registerMenuGroup(@Validated @RequestBody MenuGroupDto dto,
                                                       @PathVariable Long shopId) throws NotFoundException {
        if (service.shopIdCheck(dto.getShopId()) == shopId) {
            service.registerMenuGroup(dto);
        } else {
            throw new NotFoundException("not found shopId");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/menuGroup/{id}")
    public List<MenuGroupDto> getMenuGroup(@PathVariable Long id) {
        return service.getMenuGroup(id);
    }

}
