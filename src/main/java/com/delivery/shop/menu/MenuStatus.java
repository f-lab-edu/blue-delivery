package com.delivery.shop.menu;


public enum MenuStatus {

    DEFAULT("default"), SOLDOUT("soldout"), HIDDEN("hidden");

    private String value;

    MenuStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MenuStatus getMenuStatus(String value) {
        for (MenuStatus status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Illegal status");
    }

}
