package com.bluedelivery.api.menu.adapter;

import static com.bluedelivery.common.response.HttpResponse.*;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluedelivery.api.menu.MenuGroupController;
import com.bluedelivery.api.menu.RegisterMenuGroupDto;
import com.bluedelivery.api.menu.UpdateMenuGroupDto;
import com.bluedelivery.application.shop.adapter.MenuGroupServiceImpl;
import com.bluedelivery.common.response.HttpResponse;


@RestController
@RequestMapping("/shops")
public class MenuGroupControllerImpl implements MenuGroupController {

    private MenuGroupServiceImpl service;

    public MenuGroupControllerImpl(MenuGroupServiceImpl service) {
        this.service = service;
    }

    @Override
    @PostMapping("/{shopId}/menu-groups")
    public ResponseEntity<HttpResponse> registerMenuGroup(@PathVariable Long shopId,
                                                          @Valid @RequestBody RegisterMenuGroupDto dto) {
        dto.setShopId(shopId);
        service.registerMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response(dto));
    }

    @Override
    @PutMapping("/{shopId}/menu-groups/{id}")
    public ResponseEntity<HttpResponse> updateGroups(@PathVariable Long shopId,
                                                     @PathVariable Long id,
                                                     @Valid @RequestBody UpdateMenuGroupDto dto) {
        dto.setShopId(shopId);
        dto.setId(id);
        service.updateMenuGroup(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response(dto));
    }

    @Override
    @DeleteMapping("/{shopId}/menu-groups/{id}")
    public ResponseEntity<HttpResponse> deleteGroups(@PathVariable Long shopId,
                                                     @PathVariable Long id) {
        service.deleteMenuGroup(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpResponse.response(id));
    }

}
