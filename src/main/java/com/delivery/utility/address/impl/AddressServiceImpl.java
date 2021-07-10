package com.delivery.utility.address.impl;

import static com.delivery.response.ErrorCode.ADDRESS_DOES_NOT_EXIST;

import org.springframework.stereotype.Service;

import com.delivery.exception.ApiException;
import com.delivery.user.domain.User;
import com.delivery.utility.address.domain.Address;
import com.delivery.utility.address.domain.AddressService;
import com.delivery.utility.address.domain.BuildingInfo;
import com.delivery.utility.address.domain.BuildingInfoRepository;

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
