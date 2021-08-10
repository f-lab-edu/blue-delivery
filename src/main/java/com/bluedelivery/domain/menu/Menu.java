package com.bluedelivery.domain.menu;

import static com.bluedelivery.domain.order.ExceptionMessage.ORDERED_AND_MENU_ARE_DIFFERENT;

import java.util.List;
import java.util.Objects;

import com.bluedelivery.domain.order.OrderItem;

import lombok.Builder;

@Builder
public class Menu {
    
    private Long id;
    private Long menuGroupId;
    private String name;
    private int price;
    private String composition;
    private String content;
    private MenuStatus status;
    private List<MenuOptionGroup> menuOptionGroup;
    private boolean isMain;
    
    public enum MenuStatus {
        DEFAULT, SOLDOUT, HIDDEN;
    }
    
    public Menu() {
    }
    
    public Menu(Long id, Long menuGroupId, String name, int price, String composition, String content,
                MenuStatus status, List<MenuOptionGroup> menuOptionGroup, boolean isMain) {
        this.id = id;
        this.menuGroupId = menuGroupId;
        this.name = name;
        this.price = price;
        this.composition = composition;
        this.content = content;
        this.status = status;
        this.menuOptionGroup = menuOptionGroup;
        this.isMain = isMain;
    }
    
    public void validate(OrderItem orderItem) {
        if (!isEqualTo(orderItem)) {
            throw new IllegalStateException(ORDERED_AND_MENU_ARE_DIFFERENT);
        }
        for (OrderItem.OrderItemOptionGroup orderGroup : orderItem.getOrderItemOptionGroups()) {
            validateOptionGroupWith(orderGroup);
        }
    }
    
    private void validateOptionGroupWith(OrderItem.OrderItemOptionGroup orderGroup) {
        menuOptionGroup.stream()
                .filter( x-> x.getId() == orderGroup.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(ORDERED_AND_MENU_ARE_DIFFERENT))
                .validate(orderGroup);
    }
    
    private boolean isEqualTo(OrderItem orderItem) {
        return this.name.equals(orderItem.getMenuName())
                && this.price == orderItem.getPrice();
    }
    
    public Long getId() {
        return id;
    }
    
    public Long getMenuGroupId() {
        return menuGroupId;
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
    
    public MenuStatus getStatus() {
        return status;
    }
    
    public List<MenuOptionGroup> getMenuOptionGroup() {
        return menuOptionGroup;
    }
    
    public boolean isMain() {
        return isMain;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Menu menu = (Menu) obj;
        return price == menu.price
                && Objects.equals(id, menu.id)
                && Objects.equals(menuGroupId, menu.menuGroupId)
                && Objects.equals(name, menu.name)
                && Objects.equals(composition, menu.composition)
                && Objects.equals(content, menu.content)
                && status == menu.status
                && Objects.equals(menuOptionGroup, menu.menuOptionGroup)
                && Objects.equals(isMain, menu.isMain);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, menuGroupId, name, price, composition, content, status, menuOptionGroup, isMain);
    }
}
