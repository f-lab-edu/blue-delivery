package com.bluedelivery.application.shop;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.bluedelivery.domain.address.AddressService;
import com.bluedelivery.domain.shop.DeliveryArea;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AddressMapper {
    
    private final AddressService addressService;
    
    public List<DeliveryArea> deliveryAreas(List<String> codes) {
        List<DeliveryArea> deliveryAreas = addressService.getTowns(codes)
                .stream()
                .map(DeliveryArea::of)
                .collect(toList());
        validate(codes, deliveryAreas);
        return deliveryAreas;
    }
    
    private void validate(List<String> codes, List<DeliveryArea> deliveryAreas) {
        if (!foundAll(codes, deliveryAreas)) {
            throw new IllegalArgumentException("존재하지 않는 지역이 포함되어 있습니다.");
        }
    }
    
    private boolean foundAll(List<String> codes, List<DeliveryArea> deliveryAreas) {
        return !Objects.equals(deliveryAreas.size(), codes.size());
    }
}
