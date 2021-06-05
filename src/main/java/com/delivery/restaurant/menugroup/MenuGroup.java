package com.delivery.restaurant.menugroup;

import java.util.List;

import com.delivery.restaurant.menu.Menu;

public class MenuGroup {

    private Long id;
    private String groupName;
    private String content;
    private int restaurantId;
    private List<Menu> menuList;

    public MenuGroup(Long id, String groupName, String content, int restaurantId, List<Menu> menuList) {
        this.id = id;
        this.groupName = groupName;
        this.content = content;
        this.restaurantId = restaurantId;
        this.menuList = menuList;
    }

    public Long getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getContent() {
        return content;
    }

    public int getShopId() {
        return restaurantId;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }
}
