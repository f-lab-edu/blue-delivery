package com.bluedelivery.domain.menu;

import java.util.List;

public interface MenuRepository {
    List<Menu> findAllById(Iterable<Long> ids);
}
