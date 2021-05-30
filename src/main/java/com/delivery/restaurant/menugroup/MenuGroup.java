package com.delivery.restaurant.menugroup;

import com.delivery.restaurant.menu.Menu;

import java.util.List;

public class MenuGroup {

    private Long id;
    private String groupName;
    private String content;
    private Long shopId;
    private List<Menu> menuList;

    public MenuGroup(Long id, String groupName, String content, Long shopId, List<Menu> menuList) {
        this.id = id;
        this.groupName = groupName;
        this.content = content;
        this.shopId = shopId;
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

    public Long getShopId() {
        return shopId;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }
}
