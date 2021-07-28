package com.bluedelivery.api.user;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluedelivery.api.authentication.AuthenticationRequired;
import com.bluedelivery.common.response.HttpResponse;
import com.bluedelivery.domain.authentication.Authentication;


@RequestMapping("/users")
@AuthenticationRequired
public interface UserManagementController {
    @GetMapping("/")
    ResponseEntity<HttpResponse<Authentication>> getLoggedInUser(Authentication user);
    
    /**
     * 고객 회원 탈퇴
     *
     * @param id            고객의 id
     * @param deleteRequest 탈퇴시 확인할 정보
     * @param request
     * @return 요청 성공시 204 NO_CONTENT
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteAccount(@PathVariable("id") Long id,
                                    @Valid @RequestBody DeleteAccountRequest deleteRequest,
                                    HttpServletRequest request);
    

    @PatchMapping("/{id}")
    ResponseEntity<HttpResponse<?>> updateAccount(@PathVariable Long id,
                                                  @Valid @RequestBody UpdateAccountRequest updateRequest,
                                                  HttpServletRequest request);
    
    /**
     * 고객 주소 추가
     *
     * @param id           주소를 추가할 대상 고객
     * @param addressParam 주소 정보
     * @return
     */
    @PostMapping("/{id}/addresses")
    ResponseEntity<HttpResponse<?>> addAddress(@PathVariable("id") Long id,
                                               @Valid @RequestBody AddAddressRequest addressParam);
    
    /**
     * 고객의 대표 주소 설정
     *
     * @param id        대상 고객 아이디
     * @param addressId 주소 정보
     * @return
     */
    @PostMapping("/{id}/addresses/main/{addrId}")
    ResponseEntity<HttpResponse<?>> setMainAddress(@PathVariable("id") Long id, @PathVariable("addrId") Long addressId);
    
    /**
     * 주소 목록에서 주소 삭제
     *
     * @param id        대상 고객 아이디
     * @param addressId 주소 정보
     * @return
     */
    @DeleteMapping("/{id}/addresses/{addrId}")
    ResponseEntity<?> removeAddress(@PathVariable("id") Long id, @PathVariable("addrId") Long addressId);
    
    /**
     * 고객 회원가입
     *
     * @param registerRequest 회원가입정보
     * @return 가입 성공시 201 CREATED
     */
    @PostMapping
    ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest registerRequest);
    
}
