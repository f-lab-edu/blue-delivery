package com.bluedelivery.domain.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllById(Iterable<Long> ids);

    Menu findByName(String name);

    Long countByIsMain(boolean isMain);
}
