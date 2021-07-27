package com.bluedelivery.application.address.adapter;

import static com.bluedelivery.common.response.ErrorCode.ADDRESS_DOES_NOT_EXIST;

import org.springframework.stereotype.Service;

import com.bluedelivery.application.address.AddressService;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.address.Address;
import com.bluedelivery.domain.address.AddressRepository;
import com.bluedelivery.domain.address.BuildingInfo;
import com.bluedelivery.domain.address.BuildingInfoRepository;
import com.bluedelivery.domain.user.User;

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
