package com.delivery.shop.menu;

public class UpdateMenuDto {

    private String name;

    private int price;

    private MenuStatus status;

    public UpdateMenuDto() {

    }

    public UpdateMenuDto(MenuStatus status) {
        this.status = status;
    }

    public UpdateMenuDto(String name, int price) {
        this.name = name;
        this.price = price;
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

    public MenuStatus getStatus() {
        return status;
    }

    public void setStatus(MenuStatus status) {
        this.status = status;
    }
}
