package com.delivery.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteAccountParam {
    private final Long id;
    private final String email;
    private final String password;
    
    @Getter
    @RequiredArgsConstructor
    static class DeleteAccountRequest {
        @Email
        private final String email;
        @NotBlank
        private final String password;
        
        public DeleteAccountParam toParam(Long id) {
            return new DeleteAccountParam(id, this.email, this.password);
        }
    }
}
