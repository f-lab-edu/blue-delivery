package com.delivery.restaurant.menugroup;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.delivery.restaurant.menu.Menu;

public class MenuGroupDto {

    private Long id;

    @NotNull
    private int shopId;

    private int menuId;

    @NotNull
    private String name;

    private String content;

    private List<Menu> menus;

    public MenuGroupDto(Long id, int shopId, int menuId, String name, String content, List<Menu> menus) {
        this.id = id;
        this.shopId = shopId;
        this.menuId = menuId;
        this.name = name;
        this.content = content;
        this.menus = menus;
    }

    public MenuGroupDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String groupName) {
        this.name = groupName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
