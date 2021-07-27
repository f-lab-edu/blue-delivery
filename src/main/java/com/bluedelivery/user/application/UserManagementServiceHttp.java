package com.bluedelivery.user.application;

import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluedelivery.common.address.Address;
import com.bluedelivery.common.address.AddressService;
import com.bluedelivery.common.address.BuildingInfo;
import com.bluedelivery.exception.ApiException;
import com.bluedelivery.response.ErrorCode;
import com.bluedelivery.user.domain.User;
import com.bluedelivery.user.domain.UserRepository;
import com.bluedelivery.user.web.dto.AddressParam;
import com.bluedelivery.user.web.dto.DeleteAccountParam;
import com.bluedelivery.user.web.dto.UpdateAccountParam;
import com.bluedelivery.user.web.dto.UserRegisterParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UserManagementServiceHttp implements UserManagementService {
    
    private final UserRepository userRepository;
    private final AddressService addressService;
    
    public void register(UserRegisterParam dto) {
        try {
            userRepository.save(dto.toEntity());
        } catch (RuntimeException ex) {
            throwDuplicateInfoException(ex);
        }
    }
    
    public User updateAccount(UpdateAccountParam param) {
        User foundUser = getUserByIdAndCheckNotNull(param.getId());
        foundUser.changePhone(param.getPhone());
        foundUser.changeNickname(param.getNickname());
        foundUser.setDateOfBirth(param.getDateOfBirth());
        return foundUser;
    }
    
    public void deleteAccount(DeleteAccountParam param) {
        User user = getUserByIdAndCheckNotNull(param.getId());
        user.validate(param.getPassword());
        userRepository.delete(user);
    }
    
    @Override
    public Address addAddress(AddressParam param) {
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
