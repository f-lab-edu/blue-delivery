package com.bluedelivery.common.address;

import static com.bluedelivery.response.ErrorCode.ADDRESS_DOES_NOT_EXIST;

import org.springframework.stereotype.Service;

import com.bluedelivery.exception.ApiException;
import com.bluedelivery.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    
    private final AddressRepository addressRepository;
    private final BuildingInfoRepository buildingInfoRepository;
    
    public BuildingInfo getBuildingAddress(String buildingManagementNumber) {
        return buildingInfoRepository.findById(buildingManagementNumber)
                .orElseThrow(() -> new ApiException(ADDRESS_DOES_NOT_EXIST));
    }
    
    @Override
    public Address getAddress(Long addressId, User user) {
        return addressRepository.findByIdAndUser(addressId, user)
                .orElseThrow(() -> new ApiException(ADDRESS_DOES_NOT_EXIST));
    }
}
