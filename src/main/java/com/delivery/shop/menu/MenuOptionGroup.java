package com.delivery.shop.menu;

import java.util.List;

public class MenuOptionGroup {

    private Long id;
    private String name;
    private List<Option> options;

    public MenuOptionGroup() {
    }

    public MenuOptionGroup(Long id, String name, List<Option> options) {
        this.id = id;
        this.name = name;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Option> getOptions() {
        return options;
    }
}
