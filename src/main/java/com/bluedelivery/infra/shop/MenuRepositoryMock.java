package com.bluedelivery.infra.shop;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.menu.MenuRepository;

@Component
public class MenuRepositoryMock implements MenuRepository {
    @Override
    public List<Menu> findAllById(Iterable<Long> ids) {
        return List.of(Menu.builder().build());
    }
}
