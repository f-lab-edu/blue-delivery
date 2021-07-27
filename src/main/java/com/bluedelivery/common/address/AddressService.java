package com.bluedelivery.common.address;

import com.bluedelivery.common.address.domain.Address;
import com.bluedelivery.common.address.domain.BuildingInfo;
import com.bluedelivery.exception.ApiException;
import com.bluedelivery.user.domain.User;

public interface AddressService {
    /**
     * 주어진 건물관리번호에 해당하는 건물 정보를 조회한다.
     *
     * @param buildingManagementNumber
     * @return 주소를 포함한 건물 정보
     * @throws ApiException 검색 결과가 없는 경우 (ErrorCode# ADDRESS_DOES_NOT_EXIST)
     */
    BuildingInfo getBuildingAddress(String buildingManagementNumber);
    
    /**
     * 주어진 주소 id와 유저 정보로 주소를 조회한다.
     *
     * @param addressId 주소 id
     * @param user      유저 객체
     * @return * @throws ApiException 검색 결과가 없는 경우 (ErrorCode# ADDRESS_DOES_NOT_EXIST)
     */
    Address getAddress(Long addressId, User user);
}
