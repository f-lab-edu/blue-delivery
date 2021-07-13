package com.delivery.shop.menu;

import static com.delivery.shop.menu.Menu.*;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterMenuDto {

    private Long menuGroupId;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private int price;

    private String composition; // 구성

    private String content; // 설명

    private MenuStatus status = MenuStatus.DEFAULT; // 메뉴 상태

    private List<MenuOptionGroup> menuOptionGroup;

    private boolean isMain = false;

    public RegisterMenuDto() {
    }

    Menu toEntity() {
        return Menu.builder()
                .menuGroupId(getMenuGroupId())
                .name(getName())
                .price(getPrice())
                .composition(getComposition())
                .status(getStatus())
                .menuOptionGroup(getMenuOptionGroup())
                .isMain(getIsMain())
                .build();
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public void setMenuGroupId(Long menuGroupId) {
        this.menuGroupId = menuGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MenuStatus getStatus() {
        return status;
    }

    public void setStatus(MenuStatus status) {
        this.status = status;
    }

    public List<MenuOptionGroup> getMenuOptionGroup() {
        return menuOptionGroup;
    }

    public void setMenuOptionGroup(List<MenuOptionGroup> menuOptionGroup) {
        this.menuOptionGroup = menuOptionGroup;
    }

    public boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(boolean main) {
        isMain = main;
    }
}
