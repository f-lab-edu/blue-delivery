package com.bluedelivery.application.shop.adapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bluedelivery.application.shop.OrderRankingStrategy;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopRepository;
import com.bluedelivery.order.domain.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HighestOrderRanking implements OrderRankingStrategy {

    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final RedisTemplate redisTemplate;

    public HighestOrderRanking(OrderRepository orderRepository, ShopRepository shopRepository,
                               RedisTemplate redisTemplate) {
        this.orderRepository = orderRepository;
        this.shopRepository = shopRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List getShopRanking() {
        int key = LocalDateTime.now().getHour();
        Set set = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 10);

        List rankingList = new ArrayList(set);

        return rankingList;
    }


    @Scheduled(cron = "0 0 0/1 * * *")
    private void addShopRankingForRedis() {
        int key = LocalDateTime.now().getHour();

        for (int i = 0; i < findAllShops().size(); i++) {
            redisTemplate.opsForZSet().add(key, findAllShops().get(i).getName(), findAllTotalOrdersByShopId()[i]);
        }
        log.info("주문 많은 순 Ranking 업데이트");
    }


    private double[] findAllTotalOrdersByShopId() {
        int size = findAllShops().size();
        List<Long> list = new ArrayList();
        double[] result = new double[size];

        for (Long i = 1L; i - 1 < size; i++) {
            list.add(orderRepository.countTotalOrdersByShopId(i));
        }
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }


    private List<Shop> findAllShops() {
        List<Shop> shopList = shopRepository.findAll()
                .stream()
                .filter(s -> s.isOpen())
                .collect(Collectors.toList());
        return shopList;
    }

}
