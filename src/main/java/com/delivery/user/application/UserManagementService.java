package com.delivery.user.application;

import java.util.Optional;

import com.delivery.user.Authentication;
import com.delivery.user.domain.User;
import com.delivery.user.web.dto.AddressParam;
import com.delivery.user.web.dto.DeleteAccountParam;
import com.delivery.user.web.dto.UpdateAccountParam;
import com.delivery.user.web.dto.UserLoginParam;
import com.delivery.user.web.dto.UserRegisterParam;
import com.delivery.utility.address.Address;

public interface UserManagementService {
    
    /**
     * 회원 가입
     *
     * @param param
     */
    void register(UserRegisterParam param);
    
    /**
     * 고객 로그인
     *
     * @param param
     * @return 성공시 인증 객체를 담은 Optional, 실패시 비어있는 Optional
     */
    Optional<Authentication> login(UserLoginParam param);
    
    /**
     * 고객 정보 수정
     *
     * @param user 인증된 유저
     * @param param
     * @return 수정된 유저
     */
    User updateAccount(Authentication user, UpdateAccountParam param);
    
    /**
     * 고객 회원 탈퇴
     *
     * @param param
     */
    void deleteAccount(DeleteAccountParam param);
    
    /**
     * 고객 주소 추가
     *
     * @param addressParam 유저 id와 주소 정보
     */
    Address addAddress(AddressParam addressParam);
    
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
