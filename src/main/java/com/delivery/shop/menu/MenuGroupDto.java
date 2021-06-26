package com.delivery.shop.menu;

import java.util.List;

import javax.validation.constraints.NotNull;

public class MenuGroupDto {

    private Long id;

    @NotNull
    private Long shopId;

    @NotNull
    private String name;

    private String content;

    private List<RegisterMenuDto> menus;

    public MenuGroupDto(Long id, Long shopId, String name, String content, List<RegisterMenuDto> menus) {
        this.id = id;
        this.shopId = shopId;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public List<RegisterMenuDto> getMenus() {
        return menus;
    }

    public void setMenus(List<RegisterMenuDto> menus) {
        this.menus = menus;
    }

    public boolean checkShopId(Long id) {
        return this.shopId == id;
    }
}
