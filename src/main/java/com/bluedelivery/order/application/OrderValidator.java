package com.bluedelivery.order.application;

import static com.bluedelivery.order.domain.ExceptionMessage.*;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bluedelivery.domain.menu.Menu;
import com.bluedelivery.domain.menu.MenuRepository;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopRepository;
import com.bluedelivery.domain.user.User;
import com.bluedelivery.domain.user.UserRepository;
import com.bluedelivery.order.domain.Order;
import com.bluedelivery.order.domain.OrderItem;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderValidator {
    
    private final MenuRepository menuRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    
    public void validate(Order order) {
        List<Menu> menus = getMenus(order.getOrderItemIds());
        Shop shop = getShop(order.getShopId());
        getUser(order.getUserId());
        
        shop.validate(order);
        order.validate(menus);
    }
    
    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(ORDER_USER_DOES_NOT_EXIST));
    }
    
    private Shop getShop(Long shopId) {
        return shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException(SHOP_DOES_NOT_EXIST));
    }
    
    private List<Menu> getMenus(List<Long> menuIds) {
        return menuRepository.findAllById(menuIds);
    }
    
}
