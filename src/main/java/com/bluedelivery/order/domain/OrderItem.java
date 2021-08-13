package com.bluedelivery.order.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bluedelivery.order.interfaces.Cart;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Entity
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;
    
    private Long menuId;
    private String menuName;
    private int price;
    private int quantity;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ORDER_ITEM_ID")
    private List<OrderItemOptionGroup> orderItemOptionGroups = new ArrayList<>();
    
    protected OrderItem() {
    }
    
    @Builder
    public OrderItem(Long id, Long menuId, String menuName, int price, int quantity,
                     List<OrderItemOptionGroup> orderItemOptionGroups) {
        this.id = id;
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.quantity = quantity;
        this.orderItemOptionGroups.addAll(orderItemOptionGroups);
    }
    
    public Long getMenuId() {
        return this.menuId;
    }
    
    public String getMenuName() {
        return menuName;
    }
    
    public int getPrice() {
        return price;
    }
    
    public List<OrderItemOptionGroup> getOrderItemOptionGroups() {
        return Collections.unmodifiableList(orderItemOptionGroups);
    }
    
    public int totalOrderAmount() {
        return (price * quantity) + orderItemOptionGroups.stream().mapToInt(group -> group.totalOrderAmount()).sum();
    }
    
    public static OrderItem from(Cart.CartItem cartItem) {
        return OrderItem.builder()
                .menuId(cartItem.getMenuId())
                .menuName(cartItem.getName())
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .orderItemOptionGroups(OrderItemOptionGroup.fromList(cartItem.getCartItemOptionGroups()))
                .build();
    }
    
    // TODO OrderItemOptionGroup, OrderOptionGroup의 id, name 필드명 수정 (MenuOption~)
    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Entity
    @Table(name = "ORDER_ITEM_OPTION_GROUP")
    public static class OrderItemOptionGroup {
        
        @Id @GeneratedValue
        @Column(name = "ORDER_OPTION_GROUP_ID")
        private Long orderOptionGroupId;
    
        @Column(name = "MENU_OPTION_GROUP_ID")
        private Long id;
    
        @Column(name = "MENU_OPTION_GROUP_NAME")
        private String name;
        
        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "ORDER_OPTION_GROUP_ID")
        private List<OrderItemOption> orderItemOptions = new ArrayList<>();
    
        @Builder
        public OrderItemOptionGroup(Long orderOptionGroupId,
                Long id, String name, List<OrderItemOption> orderItemOptions) {
            this.orderOptionGroupId = orderOptionGroupId;
            this.id = id;
            this.name = name;
            this.orderItemOptions.addAll(orderItemOptions);
        }
    
        public static List<OrderItemOptionGroup> fromList(List<Cart.CartItemOptionGroup> cartItemOptionGroups) {
            return cartItemOptionGroups.stream()
                    .map(OrderItemOptionGroup::from)
                    .collect(Collectors.toList());
        }
    
        private static OrderItemOptionGroup from(Cart.CartItemOptionGroup cartItemOptionGroup) {
            return OrderItemOptionGroup.builder()
                    .id(cartItemOptionGroup.getId())
                    .name(cartItemOptionGroup.getName())
                    .orderItemOptions(OrderItem.fromList(cartItemOptionGroup.getOrderItemOptions()))
                    .build();
        }
    
        public int totalOrderAmount() {
            return orderItemOptions.stream().mapToInt(option -> option.getPrice()).sum();
        }
    }
    
    private static List<OrderItemOption> fromList(List<Cart.CartItemOption> cartItemOptions) {
        return cartItemOptions.stream()
                .map(OrderItemOption::from)
                .collect(Collectors.toList());
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @EqualsAndHashCode
    @Entity
    @Table(name = "ORDER_ITEM_OPTION")
    public static class OrderItemOption {
    
        @Id @GeneratedValue
        @Column(name = "ORDER_OPTION_ID")
        private Long orderOptionGroupId;
    
        @Column(name = "MENU_OPTION_ID")
        private Long id;
        
        @Column(name = "MENU_OPTION_NAME")
        private String name;
        private int price;
    
        @Builder
        public OrderItemOption(Long id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    
        public static OrderItemOption from(Cart.CartItemOption cartItemOption) {
            return OrderItemOption.builder()
                    .id(cartItemOption.getId())
                    .name(cartItemOption.getName())
                    .price(cartItemOption.getPrice())
                    .build();
        }
    }
}
