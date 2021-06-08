package com.delivery.shop.menu;

import javax.validation.constraints.NotNull;

public class MenuDto {

    private Long id;

    @NotNull
    private Long groupId;

    @NotNull
    private String name;

    @NotNull
    private int price;

    public MenuDto(Long id, Long groupId, @NotNull String name, @NotNull int price) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.price = price;
    }

    public MenuDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Long getGroupId() {
        return groupId;
    }
}
