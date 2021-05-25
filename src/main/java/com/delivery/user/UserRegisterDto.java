package com.delivery.user;

import com.delivery.utility.RegexConstants;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserRegisterDto {

	@NotNull(message = "must not null")
	@Email(message = "이메일 형식이어야 합니다.")
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

	@NotNull(message = "must not null")
	@Pattern(regexp = RegexConstants.PASSWORD,
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

	public User toEntity() {
		return new User(
				this.email,
				this.nickname,
				this.phone,
				this.password,
				this.dateOfBirth
		);
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
}
