package com.bluedelivery.domain.address;

import static com.bluedelivery.common.response.ErrorCode.ADDRESS_DOES_NOT_EXIST;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressService {
    
    private final AddressRepository addressRepository;
    private final BuildingInfoRepository buildingInfoRepository;
    private final CityToDongRepository cityToDongRepository;
    
    /**
     * 주어진 건물관리번호에 해당하는 건물 정보를 조회한다.
     *
     * @param buildingManagementNumber
     * @return 주소를 포함한 건물 정보
     * @throws ApiException 검색 결과가 없는 경우 (ErrorCode# ADDRESS_DOES_NOT_EXIST)
     */
    public BuildingInfo getBuildingAddress(String buildingManagementNumber) {
        return buildingInfoRepository.findById(buildingManagementNumber)
                .orElseThrow(() -> new ApiException(ADDRESS_DOES_NOT_EXIST));
    }
    
    /**
     * 주어진 주소 id와 유저 정보로 주소를 조회한다.
     *
     * @param addressId 주소 id
     * @param user      유저 객체
     * @return * @throws ApiException 검색 결과가 없는 경우 (ErrorCode# ADDRESS_DOES_NOT_EXIST)
     */
    public Address getAddress(Long addressId, User user) {
        return addressRepository.findByIdAndUser(addressId, user)
                .orElseThrow(() -> new ApiException(ADDRESS_DOES_NOT_EXIST));
    }
    
    public List<CityToDong> getTowns(List<String> townCodes) {
        return cityToDongRepository.findAllById(townCodes);
    }
}
