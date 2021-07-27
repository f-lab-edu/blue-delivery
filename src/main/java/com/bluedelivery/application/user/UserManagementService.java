package com.bluedelivery.application.user;

import com.bluedelivery.domain.address.Address;
import com.bluedelivery.domain.user.User;

public interface UserManagementService {
    
    /**
     * 회원 가입
     *
     * @param param
     */
    void register(UserRegisterTarget param);
    
    User updateAccount(UpdateAccountTarget param);
    
    /**
     * 고객 회원 탈퇴
     *
     * @param param
     */
    void deleteAccount(DeleteAccountTarget param);
    
    /**
     * 고객 주소 추가
     *
     * @param addAddressTarget 유저 id와 주소 정보
     */
    Address addAddress(AddAddressTarget addAddressTarget);
    
    /**
     * 고객 주소를 대표 주소로 지정
     * 존재하지 않는 주소일 경우 ApiException 발생
     *
     * @param id    고객 id
     * @param addressId 대표로 지정할 주소 id
     * @return 대표 주소 지정 여부를 반환. false인 경우 주소 목록에 존재하지 않는 경우이다.
     */
    boolean setMainAddress(Long id, Long addressId);
    
    /**
     * 주소 목록에서 주어진 주소를 삭제한다.
     * 만약 주어진 주소가 존재하지 않는 주소였다면 ApiException 발생
     *
     * @param id    고객 id
     * @param addressId 삭제할 주소 id
     * @return 삭제 성공 여부를 반환. false이면 애초에 존재하지 않은 경우이다.
     */
    boolean removeAddress(Long id, Long addressId);
    
}
