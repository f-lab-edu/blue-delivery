package com.bluedelivery.application.shop;

import static com.bluedelivery.common.response.ErrorCode.*;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.api.shop.UpdateCategoryRequest;
import com.bluedelivery.api.shop.UpdateClosingDaysRequest;
import com.bluedelivery.application.category.CategoryManagerService;
import com.bluedelivery.application.shop.dto.BusinessHourParam;
import com.bluedelivery.application.shop.dto.BusinessHoursTarget;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.businesshour.BusinessHour;
import com.bluedelivery.domain.businesshour.BusinessHourRepository;
import com.bluedelivery.domain.businesshour.BusinessHours;
import com.bluedelivery.domain.businesshour.DayOfWeekMapper;
import com.bluedelivery.domain.closingday.LegalHolidayClosing;
import com.bluedelivery.domain.closingday.Suspension;
import com.bluedelivery.domain.shop.Shop;
import com.bluedelivery.domain.shop.ShopCategory;
import com.bluedelivery.domain.shop.ShopCategoryRepository;
import com.bluedelivery.domain.shop.ShopRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ShopUpdateService {
    
    private final ShopRepository shopRepository;
    private final CategoryManagerService categoryManagerService;
    private final BusinessHourRepository businessHourRepository;
    private final ShopCategoryRepository shopCategoryRepository;
    
    public BusinessHours updateBusinessHour(BusinessHoursTarget target) {
        Shop shop = getShop(target.getShopId());
        List<BusinessHour> all = businessHourRepository.findAllByShop(shop);
        // 입력받은 영업일 정책을 실제 요일과 매핑
        Map<DayOfWeek, BusinessHourParam> resolved =
                DayOfWeekMapper.map(target.getBusinessHourType(), target.getBusinessHours());
        
        // 영업일이 없으면 입력을 그대로 저장
        if (all.size() == 0) {
            List<BusinessHour> list = resolved.entrySet().stream()
                    .map(entry -> entry.getValue().toEntity(entry.getKey()))
                    .collect(Collectors.toList());
            Collections.sort(list);
            return new BusinessHours(list);
        }
        
        for (BusinessHour businessHour : all) {
            businessHour.update(resolved.get(businessHour.getDayOfWeek()));
        }
        return new BusinessHours(all);
    }
    
    public void editIntroduce(Long id, String introduce) {
        Shop shop = getShop(id);
        shop.editIntroduce(introduce);
    }
    
    public void editPhoneNumber(Long id, String phone) {
        Shop shop = getShop(id);
        shop.editPhoneNumber(phone);
    }
    
    public void editDeliveryAreaGuide(Long id, String guide) {
        Shop shop = getShop(id);
        shop.editDeliveryAreaGuide(guide);
    }
    
    public void rename(Long id, String name) {
        Shop shop = getShop(id);
        shop.rename(name);
    }
    
    public void updateCategory(Long shopId, UpdateCategoryRequest dto) {
        Shop shop = getShop(shopId);
        shopCategoryRepository.deleteByShopId(shopId);
        List<ShopCategory> shopCategories = categoryManagerService.getCategoriesById(dto.getCategoryIds()).stream()
                .map(category -> new ShopCategory(shop, category))
                .collect(Collectors.toList());
        shopCategoryRepository.saveAll(shopCategories);
    }
    
    public void updateClosingDays(Long id, UpdateClosingDaysRequest closingDays) {
        Boolean closingOnLegalHolidays = closingDays.getLegalHolidays();
        List<TemporaryClosingParam> temporaries = closingDays.getTemporaryClosing();
        List<RegularClosingParam> regulars = closingDays.getRegularClosing();
        Shop shop = getShop(id);
        
        if (closingOnLegalHolidays) {
            shop.addClosingDayPolicy(new LegalHolidayClosing());
        }
        temporaries.stream().forEach(
                temporary -> shop.addClosingDayPolicy( temporary.toEntity()));
        regulars.stream().forEach(
                regular -> shop.addClosingDayPolicy(regular.toEntity()));
    }
    
    public void expose(Long shopId, Boolean status) {
        Shop shop = getShop(shopId);
        shop.updateExposeStatus(status);
    }
    
    public void suspend(Long shopId, Suspension suspension) {
        Shop shop = getShop(shopId);
        shop.suspend(suspension);
    }
    
    private Shop getShop(Long id) {
        return shopRepository.findById(id).orElseThrow(() -> new ApiException(SHOP_DOES_NOT_EXIST));
    }
}
