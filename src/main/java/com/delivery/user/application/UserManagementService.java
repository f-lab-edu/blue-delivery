package com.delivery.user.application;

import com.delivery.user.Authentication;
import com.delivery.user.DeleteAccountParam;
import com.delivery.user.UpdateAccountParam;
import com.delivery.user.UserLoginParam;
import com.delivery.user.UserRegisterParam;

public interface UserManagementService {
    
    /**
     * 회원 가입
     * @param param
     */
    void register(UserRegisterParam param);
    
    /**
     * 고객 로그인
     * @param param
     * @return 성공시 인증 객체를 반환
     */
    Authentication login(UserLoginParam param);
    
    /**
     * 고객 정보 수정
     * @param param
     */
    void updateAccount(UpdateAccountParam param);
    
    /**
     * 고객 회원 탈퇴
     * @param param
     */
    void deleteAccount(DeleteAccountParam param);
    
}
