package com.delivery.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class DeleteAccountParam {
    private Long id;
    private String email;
    private String password;
    
    public DeleteAccountParam(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    static class DeleteAccountRequest {
        @Email
        private String email;
        @NotBlank
        private String password;
    
        public DeleteAccountRequest() {
        }
    
        public DeleteAccountRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }
        
        public DeleteAccountParam toParam(Long id) {
            return new DeleteAccountParam(id, this.email, this.password);
        }
        
        public String getEmail() {
            return email;
        }
        
        public String getPassword() {
            return password;
        }
    }
}
