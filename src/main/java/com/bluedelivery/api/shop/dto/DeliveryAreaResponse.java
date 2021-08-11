package com.bluedelivery.api.shop.dto;

import java.util.List;

import com.bluedelivery.domain.shop.DeliveryArea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DeliveryAreaResponse {
    private Long shopId;
    private List<DeliveryArea> deliveryAreas;
}
