package com.delivery.user;

import com.delivery.utility.RegexConstants;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserUpdateAccountDto {

	private String email;

	@NotBlank(message = "닉네임은 공백이 될 수 없습니다.")
	private String nickname;

	@NotNull(message = "must not null")
	@Pattern(regexp = RegexConstants.PHONE,
			message = "01로 시작하는 10-11자리 숫자여야 합니다.")
	private String phone;

	@NotNull(message = "must not null")
	@Pattern(regexp = RegexConstants.PASSWORD,
			message = "알파벳, 숫자, 특수문자가 각 1개이상 포함된 8~20 글자여야 합니다.")
	private String password;

	@NotNull(message = "생년월일이 입력되어야 합니다.")
	@Past(message = "올바르지 않은 생년월일 입니다.")
	private LocalDate dateOfBirth;

	public UserUpdateAccountDto(String email, String nickname, String phone, String password, LocalDate dateOfBirth) {
		this.email = email;
		this.nickname = nickname;
		this.phone = phone;
		this.password = password;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}