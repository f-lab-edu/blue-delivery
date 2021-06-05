package com.delivery.restaurant.menu;

import javax.validation.constraints.NotNull;

public class Menu {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int price;

    public Menu(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public String name() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
