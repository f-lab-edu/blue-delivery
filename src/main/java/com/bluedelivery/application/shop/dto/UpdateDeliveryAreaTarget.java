package com.bluedelivery.application.shop.dto;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class UpdateDeliveryAreaTarget {
    private final Long shopId;
    private final List<String> townCodes;
}
