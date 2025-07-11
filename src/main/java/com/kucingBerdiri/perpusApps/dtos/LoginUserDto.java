package com.kucingBerdiri.perpusApps.dtos;

import jakarta.validation.constraints.NotEmpty;

public class LoginUserDto {
	
	@NotEmpty(message = "username tidak boleh kosong")
	private String username;
	
	@NotEmpty(message = "password tidak boleh kosong")
    private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
