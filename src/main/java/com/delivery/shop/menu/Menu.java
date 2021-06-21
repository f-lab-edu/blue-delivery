package com.delivery.shop.menu;

import java.time.LocalDateTime;

public class Menu {

    public enum Status {
        DEFAULT, SOLDOUT, HIDDEN
    }

    private Long id;
    private Long groupId;
    private String name;
    private int price;
    private String composition;
    private String content;
    private Status status = Status.DEFAULT;
    private MenuOptionGroup menuOptionGroup;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Menu() {
    }

    public Menu(Long id, Long groupId, String name, int price, String composition, String content, Status status,
                MenuOptionGroup menuOptionGroup, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.price = price;
        this.composition = composition;
        this.content = content;
        this.status = status;
        this.menuOptionGroup = menuOptionGroup;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
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

    public String getComposition() {
        return composition;
    }

    public String getContent() {
        return content;
    }

    public Status getStatus() {
        return status;
    }

    public MenuOptionGroup getMenuOptionGroup() {
        return menuOptionGroup;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }


}
