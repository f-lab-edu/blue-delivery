package com.bluedelivery.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {

//    @Query("SELECT mg FROM MenuGroup mg WHERE mg.name = :name")
    MenuGroup findByName(String name);

}
