package com.bluedelivery.application.user.adapter;

import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.application.address.AddressService;
import com.bluedelivery.application.user.AddAddressTarget;
import com.bluedelivery.application.user.DeleteAccountTarget;
import com.bluedelivery.application.user.UpdateAccountTarget;
import com.bluedelivery.application.user.UserManagementService;
import com.bluedelivery.application.user.UserRegisterTarget;
import com.bluedelivery.common.response.ApiException;
import com.bluedelivery.common.response.ErrorCode;
import com.bluedelivery.domain.address.Address;
import com.bluedelivery.domain.address.BuildingInfo;
import com.bluedelivery.domain.user.User;
import com.bluedelivery.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UserManagementServiceHttp implements UserManagementService {
    
    private final UserRepository userRepository;
    private final AddressService addressService;
    
    public void register(UserRegisterTarget dto) {
        try {
            userRepository.save(dto.toEntity());
        } catch (RuntimeException ex) {
            throwDuplicateInfoException(ex);
        }
    }
    
    public User updateAccount(UpdateAccountTarget param) {
        User foundUser = getUserByIdAndCheckNotNull(param.getId());
        foundUser.changePhone(param.getPhone());
        foundUser.changeNickname(param.getNickname());
        foundUser.setDateOfBirth(param.getDateOfBirth());
        return foundUser;
    }
    
    public void deleteAccount(DeleteAccountTarget param) {
        User user = getUserByIdAndCheckNotNull(param.getId());
        user.validate(param.getPassword());
        userRepository.delete(user);
    }
    
    @Override
    public Address addAddress(AddAddressTarget param) {
        User user = getUserByIdAndCheckNotNull(param.getId());
        BuildingInfo buildingInfo =
                addressService.getBuildingAddress(param.getBuildingManagementNumber());
        Address address = new Address(buildingInfo, param.getDetail());
        user.addAddress(address);
        return address;
    }
    
    @Override
    public boolean setMainAddress(Long userId, Long addressId) {
        User user = getUserByIdAndCheckNotNull(userId);
        Address address = addressService.getAddress(addressId, user);
        return user.designateAsMainAddress(address);
    }
    
    @Override
    public boolean removeAddress(Long userId, Long addressId) {
        User user = getUserByIdAndCheckNotNull(userId);
        Address address = addressService.getAddress(addressId, user);
        return user.removeAddress(address);
    }
    
    private User getUserByIdAndCheckNotNull(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));
    }
    
    private void throwDuplicateInfoException(RuntimeException ex) {
        String msg = ex.getMessage().toLowerCase(Locale.ROOT);
        if (msg.contains("email")) {
            throw new ApiException(ErrorCode.DUPLICATE_EMAIL);
        }
        if (msg.contains("nickname")) {
            throw new ApiException(ErrorCode.DUPLICATE_NICKNAME);
        }
        if (msg.contains("phone")) {
            throw new ApiException(ErrorCode.DUPLICATE_PHONE);
        }
    }
}
