package com.bluedelivery.api.shop;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluedelivery.api.shop.dto.BusinessHoursRequest;
import com.bluedelivery.api.shop.dto.UpdateDeliveryAreaRequest;
import com.bluedelivery.common.response.HttpResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "가게 정보")
@RequestMapping("/shops/{shopId}")
public interface ShopUpdateController {
    
    @PutMapping("/business-hours")
    ResponseEntity<HttpResponse<?>> updateBusinessHours(
            @PathVariable Long shopId, @RequestBody @Valid BusinessHoursRequest dto);
    
    @PatchMapping("/introduce")
    void editIntroduce(@PathVariable("shopId") Long id, @RequestBody String introduce);
    
    @PatchMapping("/phone")
    void editPhoneNumber(@PathVariable("shopId") Long id,
                         @RequestBody @Valid EditPhoneRequest dto);
    
    @PatchMapping("/delivery-area-guide")
    void editDeliveryAreaGuid(@PathVariable("shopId") Long id, @RequestBody String guide);
    
    @PatchMapping("/name")
    void rename(@PathVariable("shopId") Long id, @RequestBody String name);
    
    /**
     * 해당 가게의 카테고리를 입력받은 대로 업데이트
     *
     * @param shopId 가게 id
     * @param dto    추가할 카테고리 enum name을 담은 dto
     * @return
     */
    @PutMapping("/categories")
    ResponseEntity<HttpResponse<?>> updateCategory(@PathVariable Long shopId,
                                                   @RequestBody @Valid UpdateCategoryRequest dto);
    
    /**
     * 해당 가게의 휴무일을 입력받고 업데이트한다.
     *
     * @param shopId      가게 id
     * @param closingDays 휴무일 정보
     */
    @PutMapping("/closing-days")
    void updateClosingDays(@PathVariable Long shopId,
                           @RequestBody @Valid UpdateClosingDaysRequest closingDays);
    
    /**
     * 해당 가게의 노출여부를 변경한다.
     *
     * @param shopId 가게 id
     * @param expose 가게 노출 여부
     */
    @PostMapping("/expose")
    void setExpose(@PathVariable Long shopId, @RequestBody Boolean expose);
    
    /**
     * 영업을 일시 정지시킨다.
     * 정지 기간이 NONE 인 경우, toUntil() 메소드는 from, to 가 모두 현재 시간인 엔티티를 리턴한다.
     * 즉 정지 기간이 아니게 된다.
     *
     * @param shopId     가게 ID
     * @param suspension 정지 시작 시간, 정지 기간 정보를 담고 있는 DTO
     */
    @PutMapping("/suspend")
    void suspendShop(@PathVariable Long shopId,
                     @RequestBody SuspensionRequest suspension);
    
    @ApiOperation(value = "배달 가능 지역 업데이트", notes = "읍면동 코드를 받아서 가게의 배달 가능 지역으로 설정한다.")
    @PutMapping("/delivery-areas")
    ResponseEntity<HttpResponse<?>> updateDeliveryArea(
            @PathVariable Long shopId, @RequestBody UpdateDeliveryAreaRequest dto);
    
}
