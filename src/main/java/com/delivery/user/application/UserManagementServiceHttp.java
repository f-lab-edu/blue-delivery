package com.delivery.user.application;

import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.exception.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.user.Authentication;
import com.delivery.user.domain.User;
import com.delivery.user.domain.UserRepository;
import com.delivery.user.web.dto.AddressParam;
import com.delivery.user.web.dto.DeleteAccountParam;
import com.delivery.user.web.dto.UpdateAccountParam;
import com.delivery.user.web.dto.UserLoginParam;
import com.delivery.user.web.dto.UserRegisterParam;
import com.delivery.utility.address.Address;
import com.delivery.utility.address.AddressService;
import com.delivery.utility.address.BuildingInfo;

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
    
    public Optional<Authentication> login(UserLoginParam param) {
        User found = findUserByEmailAndCheckNotNull(param.getEmail());
        boolean validate = found.validate(param.getEmail(), param.getPassword());
        if (validate) {
            return Optional.of(
                    new Authentication(found.getId(), found.getEmail(),
                            found.getNickname(), found.getPhone(), found.getDateOfBirth()));
        }
        return Optional.empty();
    }
    
    public User updateAccount(Authentication user, UpdateAccountParam param) {
        User foundUser = getUserByIdAndCheckNotNull(user.getId());
        foundUser.changePhone(param.getPhone());
        foundUser.changeNickname(param.getNickname());
        foundUser.setDateOfBirth(param.getDateOfBirth());
        return foundUser;
    }
    
    public void deleteAccount(DeleteAccountParam param) {
        User user = getUserByIdAndCheckNotNull(param.getId());
        user.validate(param.getEmail(), param.getPassword());
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
    
    private User findUserByEmailAndCheckNotNull(String email) {
        return userRepository.findByEmail(email)
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
