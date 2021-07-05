package com.delivery.user;

import static com.delivery.user.UpdateAccountParam.UpdateAccountRequest;
import static com.delivery.user.UserLoginParam.UserLoginRequest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.delivery.user.DeleteAccountParam.DeleteAccountRequest;
import com.delivery.user.UserRegisterParam.UserRegisterRequest;


@RequestMapping("/users")
public interface UserManagementController {
    
    /**
     * 고객 회원가입
     *
     * @param registerRequest 회원가입정보
     * @return 가입 성공시 201 CREATED
     */
    @PostMapping
    ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest registerRequest);
    
    /**
     * 고객 로그인
     *
     * @param loginRequest 로그인 정보
     * @param session      http session
     */
    @PostMapping("/login")
    void login(@RequestBody UserLoginRequest loginRequest, HttpSession session);
    
    /**
     * 고객 회원 탈퇴
     *
     * @param id            고객의 id
     * @param deleteRequest 탈퇴시 확인할 정보
     * @param session       http session
     * @return 요청 성공시 204 NO_CONTENT
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable("id") Long id,
                                    @Valid @RequestBody DeleteAccountRequest deleteRequest, HttpSession session);
    
    /**
     * 고객 정보 수정
     *
     * @param id            고객의 id
     * @param updateRequest 업데이트할 정보
     */
    @PutMapping("/{id}")
    void updateAccount(@PathVariable("id") Long id, @Valid @RequestBody UpdateAccountRequest updateRequest);
}
