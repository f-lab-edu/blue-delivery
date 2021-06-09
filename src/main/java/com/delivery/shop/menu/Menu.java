package com.delivery.shop.menu;

import javax.validation.constraints.NotNull;

public class Menu {

    private Long id;
    private Long groupId;
    private String name;
    private int price;

    public Menu(Long id, Long groupId, String name, int price) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.price = price;
    }

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
