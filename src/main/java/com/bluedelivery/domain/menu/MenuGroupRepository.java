package com.bluedelivery.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {

    MenuGroup findByName(String name);

}
