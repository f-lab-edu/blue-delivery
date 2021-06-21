package com.delivery.shop.menu;

public class Option {

    private Long id;
    private String name;
    private int price;

    public Option() {
    }

    public Option(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
}
