package com.delivery.restaurant;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delivery.restaurant.businesshour.BusinessHour;
import com.delivery.restaurant.businesshour.BusinessHourMapper;
import com.delivery.restaurant.businesshour.BusinessHourPolicy;

@Repository
public class RestaurantRepositoryMybatis implements RestaurantRepository {
    
    private final RestaurantMapper restaurantMapper;
    private final BusinessHourMapper businessHourMapper;
    
    public RestaurantRepositoryMybatis(RestaurantMapper restaurantMapper, BusinessHourMapper businessHourMapper) {
        this.restaurantMapper = restaurantMapper;
        this.businessHourMapper = businessHourMapper;
    }
    
    @Override
    public Restaurant findRestaurantById(Long id) {
        Restaurant restaurant = restaurantMapper.findRestaurantById(id);
        if (restaurant == null) {
            throw new IllegalArgumentException("restaurant does not exist");
        }
    
        injectBusinessHours(id, restaurant);
        return restaurant;
    }
    
    @Override
    public void updateBusinessHour(Restaurant restaurant) {
        businessHourMapper.deleteAllByRestaurantId(restaurant.getId());
        insertBusinessHour(restaurant);
    }
    
    @Override
    public void updateIntroduce(Restaurant restaurant) {
        restaurantMapper.updateIntroduce(restaurant.getId(), restaurant.getIntroduce());
    }
    
    @Override
    public void updatePhone(Restaurant restaurant) {
        restaurantMapper.updatePhone(restaurant.getId(), restaurant.getPhone());
    }
    
    @Override
    public void updateDeliveryAreaGuide(Restaurant restaurant) {
        restaurantMapper.updateDeliveryAreaGuide(restaurant.getId(), restaurant.getDeliveryAreaGuide());
    }
    
    @Override
    public void updateName(Restaurant restaurant) {
        restaurantMapper.updateName(restaurant.getId(), restaurant.getName());
    }
    
    private void insertBusinessHour(Restaurant restaurant) {
        BusinessHourPolicy bhs = restaurant.getBusinessHour();
        for (Map.Entry<DayOfWeek, BusinessHour> bh : bhs.getBusinessHours().entrySet()) {
            businessHourMapper.insert(
                    restaurant.getId(),
                    bh.getValue().getOpen(),
                    bh.getValue().getClose(),
                    bh.getKey()
            );
        }
    }
    
    private void injectBusinessHours(Long id, Restaurant restaurant) {
        Map<DayOfWeek, BusinessHour> bhs = selectBusinessHoursFromMapper(id);
        if (bhs != null) {
            BusinessHourPolicy policy = new BusinessHourPolicy();
            policy.updateAll(bhs);
            restaurant.updateBusinessHour(policy);
        }
    }
    
    // Mybatis에서 바로 Map 타입으로 받아오지 못하고, List<Map<>>로 받아오고 내부 Map의 value로 값들이 담겨오기 때문에
    // 이것을 Map<DayOfWeek, BusinessHour>로 변환해주는 역할
    private Map<DayOfWeek, BusinessHour> selectBusinessHoursFromMapper(Long id) {
        LinkedHashMap<DayOfWeek, BusinessHour> bhs = new LinkedHashMap<>();
        List<Map<DayOfWeek, BusinessHour>> list = businessHourMapper.findHoursByRestaurantId(id);
        for (Map<DayOfWeek, BusinessHour> pair : list) {
            Collection<BusinessHour> values = pair.values();
            DayOfWeek dayOfWeek = null;
            BusinessHour businessHour = null;
            for (Object o : values) {
                if (o instanceof DayOfWeek) {
                    dayOfWeek = (DayOfWeek) o;
                }
                if (o instanceof BusinessHour) {
                    businessHour = (BusinessHour) o;
                }
            }
            bhs.put(dayOfWeek, businessHour);
        }
        return bhs;
    }
}
