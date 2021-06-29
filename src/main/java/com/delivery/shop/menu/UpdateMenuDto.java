package com.delivery.shop.menu;

import javax.validation.constraints.NotNull;

import com.delivery.shop.menu.Menu.MenuStatus;

public class UpdateMenuDto {

    private Long id;

    private String name;

    private int price;

    private String composition;

    private String content;

    @NotNull
    private MenuStatus status;

    public UpdateMenuDto() {
    }

    public UpdateMenuDto(Long id, String name, int price, String composition, String content, MenuStatus status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.composition = composition;
        this.content = content;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
