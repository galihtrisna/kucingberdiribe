package com.kucingBerdiri.perpusApps.dtos;

public class UserDto {
	private Integer id;
	private String username;
    private String fullName;
    
    public UserDto(Integer id, String username, String fullName) {
    	this.id = id;
    	this.username = username;
    	this.fullName = fullName;
    }
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
