package com.delivery.restaurant.menugroup;

import com.delivery.restaurant.menu.Menu;

import java.util.List;

public class MenuGroup {

    private Long id;
    private String groupName;
    private String groupDescription;
    private List<Menu> menuList;

    public MenuGroup(Long id, String groupName, String groupDescription, List<Menu> menuList) {
        this.id = id;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.menuList = menuList;
    }

    public MenuGroup() {
    }

    public Long getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

}
