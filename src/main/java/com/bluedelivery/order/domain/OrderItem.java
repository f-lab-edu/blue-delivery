package com.bluedelivery.order.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bluedelivery.order.interfaces.Cart;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long orderItemId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    private Long menuId;
    private String menuName;
    private int price;
    private int quantity;
    
    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemOptionGroup> orderItemOptionGroups = new ArrayList<>();
    
    protected OrderItem() {
    }
    
    @Builder
    public OrderItem(Long orderItemId, Long menuId, String menuName, int price, int quantity,
                     List<OrderItemOptionGroup> orderItemOptionGroups) {
        this.orderItemId = orderItemId;
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.quantity = quantity;
        orderItemOptionGroups.forEach(item -> item.setOrderItem(this));
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
        return (price + orderItemOptionGroups.stream().mapToInt(group -> group.totalOrderAmount()).sum()) * quantity;
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
    
    public Long getOrderItemId() {
        return orderItemId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrderItem orderItem = (OrderItem) obj;
        return price == orderItem.price
                && quantity == orderItem.quantity
                && Objects.equals(menuId, orderItem.menuId)
                && Objects.equals(menuName, orderItem.menuName)
                && Objects.equals(orderItemOptionGroups, orderItem.orderItemOptionGroups);
    }
    
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Entity
    @Table(name = "ORDER_ITEM_OPTION_GROUP")
    public static class OrderItemOptionGroup {
        
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ORDER_OPTION_GROUP_ID")
        private Long orderOptionGroupId;
    
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ORDER_ITEM_ID")
        private OrderItem orderItem;
        
        @Column(name = "MENU_OPTION_GROUP_ID")
        private Long id;
    
        @Column(name = "MENU_OPTION_GROUP_NAME")
        private String name;
        
        @OneToMany(mappedBy = "orderItemOptionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<OrderItemOption> orderItemOptions = new ArrayList<>();
    
        @Builder
        public OrderItemOptionGroup(Long orderOptionGroupId, Long id,
                                    String name, List<OrderItemOption> orderItemOptions) {
            this.orderOptionGroupId = orderOptionGroupId;
            this.id = id;
            this.name = name;
            orderItemOptions.forEach(item -> item.setOrderItemOptionGroup(this));
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
    
        public void setOrderItem(OrderItem orderItem) {
            this.orderItem = orderItem;
        }
    
        public Long getOrderOptionGroupId() {
            return orderOptionGroupId;
        }
    
        public Long getId() {
            return id;
        }
    
        public String getName() {
            return name;
        }
    
        public List<OrderItemOption> getOrderItemOptions() {
            return orderItemOptions;
        }
    
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            OrderItemOptionGroup that = (OrderItemOptionGroup) obj;
            return Objects.equals(id, that.id)
                    && Objects.equals(name, that.name)
                    && Objects.equals(orderItemOptions, that.orderItemOptions);
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(orderOptionGroupId);
        }
        
    }
    
    private static List<OrderItemOption> fromList(List<Cart.CartItemOption> cartItemOptions) {
        return cartItemOptions.stream()
                .map(OrderItemOption::from)
                .collect(Collectors.toList());
    }
    
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Entity
    @Table(name = "ORDER_ITEM_OPTION")
    public static class OrderItemOption {
    
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ORDER_OPTION_ID")
        private Long orderOptionId;
    
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ORDER_OPTION_GROUP_ID")
        private OrderItemOptionGroup orderItemOptionGroup;
        
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
    
        public void setOrderItemOptionGroup(OrderItemOptionGroup orderItemOptionGroup) {
            this.orderItemOptionGroup = orderItemOptionGroup;
        }
    
        public Long getOrderOptionId() {
            return orderOptionId;
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
    
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            OrderItemOption that = (OrderItemOption) obj;
            return price == that.price
                    && Objects.equals(id, that.id)
                    && Objects.equals(name, that.name);
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(orderOptionId);
        }
    }
}
