package com.delivery.restaurant.menu;

public class Menu {

    private Long id;
    private String name;

    public Menu(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
