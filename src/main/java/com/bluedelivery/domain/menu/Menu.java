package com.bluedelivery.domain.menu;

import static com.bluedelivery.order.domain.ExceptionMessage.ORDERED_AND_MENU_ARE_DIFFERENT;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bluedelivery.order.domain.OrderItem;

import lombok.Builder;

@Builder
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "COMPOSITION")
    private String composition;

    @Column(name = "CONTENT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private MenuStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_GROUP_ID")
    private MenuGroup menuGroup;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<MenuOptionGroup> menuOptionGroup;

    private boolean isMain;
    
    public enum MenuStatus {
        DEFAULT, SOLDOUT, HIDDEN;
    }
    
    public Menu() {
    }

    public Menu(Long id, String name, int price, String composition, String content, MenuStatus status,
                MenuGroup menuGroup, List<MenuOptionGroup> menuOptionGroup, boolean isMain) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.composition = composition;
        this.content = content;
        this.status = status;
        this.menuGroup = menuGroup;
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

    public void setId(Long id) {
        this.id = id;
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

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MenuStatus getStatus() {
        return status;
    }

    public void setStatus(MenuStatus status) {
        this.status = status;
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
    }

    public List<MenuOptionGroup> getMenuOptionGroup() {
        return menuOptionGroup;
    }

    public void setMenuOptionGroup(List<MenuOptionGroup> menuOptionGroup) {
        this.menuOptionGroup = menuOptionGroup;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
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
                && Objects.equals(name, menu.name)
                && Objects.equals(composition, menu.composition)
                && Objects.equals(content, menu.content)
                && status == menu.status
                && Objects.equals(menuOptionGroup, menu.menuOptionGroup)
                && Objects.equals(isMain, menu.isMain);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, composition, content, status, menuOptionGroup, isMain);
    }
}
