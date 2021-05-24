package com.delivery.user;

public class UserLoginDto {

	private String email;
	private String userPassword;

	public UserLoginDto(String email, String userPassword) {
		this.email = email;
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
