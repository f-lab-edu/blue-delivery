package com.bluedelivery.api.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.bluedelivery.application.user.DeleteAccountTarget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAccountRequest {
    
    @Email
    private String email;
    @NotBlank
    private String password;
    
    public DeleteAccountTarget toParam(Long id) {
        return new DeleteAccountTarget(id, this.email, this.password);
    }
}
