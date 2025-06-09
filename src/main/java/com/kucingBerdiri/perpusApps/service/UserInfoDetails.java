package com.kucingBerdiri.perpusApps.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kucingBerdiri.perpusApps.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserInfoDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private String fullName;
	private String username; 
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(User userInfo) {
    	this.fullName = userInfo.getFullName();
        this.username = userInfo.getUsername(); 
        this.password = userInfo.getPassword();
        this.authorities = List.of(userInfo.getRole().split(","))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }
    

    public String getfullName() {
        return fullName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getPassword() {
		
		return password;
	}
}
