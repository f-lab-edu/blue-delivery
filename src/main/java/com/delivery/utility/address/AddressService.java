package com.delivery.utility.address;

import com.delivery.user.domain.User;

public interface AddressService {
    BuildingInfo getBuildingAddress(String buildingManagementNumber);
    
    Address getAddress(Long addressId, User user);
}
