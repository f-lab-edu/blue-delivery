package com.delivery.shop.shop;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.delivery.response.HttpResponse;
import com.delivery.shop.businesshour.UpdateBusinessHoursDto;
import com.delivery.shop.suspension.SuspensionRequest;

@RequestMapping("/shops/{id}")
public interface ShopUpdateController {
    @PutMapping("/business-hours")
    void updateBusinessHours(@PathVariable("id") Long id, @RequestBody @Valid UpdateBusinessHoursDto dto);
    
    @PatchMapping("/introduce")
    void editIntroduce(@PathVariable("id") Long id, @RequestBody String introduce);
    
    @PatchMapping("/phone")
    void editPhoneNumber(@PathVariable("id") Long id,
                         @RequestBody @Valid EditPhoneRequest dto);
    
    @PatchMapping("/delivery-area-guide")
    void editDeliveryAreaGuid(@PathVariable("id") Long id, @RequestBody String guide);
    
    @PatchMapping("/name")
    void rename(@PathVariable("id") Long id, @RequestBody String name);
    
    /**
     * 해당 가게의 카테고리를 입력받은 대로 업데이트
     *
     * @param shopId 가게 id
     * @param dto    추가할 카테고리 enum name을 담은 dto
     */
    @PutMapping("/categories")
    void updateCategory(@PathVariable("id") Long shopId,
                        @RequestBody @Valid UpdateCategoryRequest dto);
    
    /**
     * 해당 가게의 휴무일을 입력받고 업데이트한다.
     *
     * @param shopId      가게 id
     * @param closingDays 휴무일 정보
     */
    @PutMapping("/closing-days")
    void updateClosingDays(@PathVariable("id") Long shopId,
                           @RequestBody @Valid UpdateClosingDaysRequest closingDays);
    
    /**
     * 해당 가게의 노출여부를 변경한다.
     *
     * @param shopId 가게 id
     * @param expose 가게 노출 여부
     */
    @PostMapping("/expose")
    void setExpose(@PathVariable("id") Long shopId, @RequestBody Boolean expose);
    
    /**
     * 영업을 일시 정지시킨다.
     * 정지 기간이 NONE 인 경우, toUntil() 메소드는 from, to 가 모두 현재 시간인 엔티티를 리턴한다.
     * 즉 정지 기간이 아니게 된다.
     *
     * @param shopId     가게 ID
     * @param suspension 정지 시작 시간, 정지 기간 정보를 담고 있는 DTO
     */
    @PutMapping("/suspend")
    void suspendShop(@PathVariable("id") Long shopId,
                     @RequestBody SuspensionRequest suspension);
    
    @PutMapping("/delivery-area")
    void updateDeliveryArea(@PathVariable("id") Long shopId,
                                                       @RequestBody UpdateDeliveryAreaRequest request);
}
