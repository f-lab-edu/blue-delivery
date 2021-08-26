package com.bluedelivery.domain.menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllById(Iterable<Long> ids);

    Menu findByName(String name);

    @Query(value = "SELECT COUNT(m.isMain) FROM Menu m WHERE m.isMain = true")
    Long countIsMain();
}
