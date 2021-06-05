package com.delivery.restaurant.menugroup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("restaurants/{restaurantId}/menuGroups")
    public ResponseEntity<MenuGroup> registerMenuGroup(@Validated @RequestBody MenuGroupDto dto,
                                                       @PathVariable Long restaurantId) {
        if (!dto.checkRestaurantId(restaurantId)) {
            throw new NotFoundIdException("not found restaurantId");
        }
        service.registerMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
