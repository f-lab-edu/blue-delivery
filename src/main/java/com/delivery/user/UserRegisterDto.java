package com.delivery.user;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserRegisterDto {
    @NotNull(message = "must not null")
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "닉네임은 공백이 될 수 없습니다.")
    private String nickname;

    @NotNull(message = "must not null")
    @Pattern(regexp = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$",
            message = "01로 시작하는 10-11자리 숫자여야 합니다.")
    private String phone;

    @NotNull(message = "must not null")
    @Pattern(regexp = "(?=.*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣])(?=.*[0-9])(?=.*[^\\w\\s]).{8,20}",
            message = "알파벳, 숫자, 특수문자가 각 1개이상 포함된 8~20 글자여야 합니다.")
    private String password;

    @NotNull(message = "must not null")
    @Pattern(regexp = "(?=.*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣])(?=.*[0-9])(?=.*[^\\w\\s]).{8,20}",
            message = "알파벳, 숫자, 특수문자가 각 1개이상 포함된 8~20 글자여야 합니다.")
    private String confirmedPassword;

    @NotNull(message = "생년월일이 입력되어야 합니다.")
    @Past(message = "올바르지 않은 생년월일 입니다.")
    private LocalDate dateOfBirth;

    public UserRegisterDto(String email, String nickname, String phone, String password, String confirmedPassword, LocalDate dateOfBirth) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
