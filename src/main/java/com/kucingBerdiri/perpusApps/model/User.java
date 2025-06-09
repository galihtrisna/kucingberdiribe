package com.kucingBerdiri.perpusApps.model;


import java.time.LocalDateTime;

import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.kucingBerdiri.perpusApps.model.base.AbstractEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class User extends AbstractEntity {

	
	
	@NotEmpty(message = "username tidak boleh kosong")
	@Column(unique = true, nullable = false)
	private String username;
	
	@NotEmpty(message = "Password tidak boleh kosong")
	private String password;
	
	@NotEmpty(message = "Nama tidak boleh kosong")
	private String fullName;
	
	@NotEmpty(message = "Role tidak boleh kosong")
	private String role;
	
	
	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedAt;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BorrowRec> borrowRecords;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> reviews;

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	 
	
	
	
	
	
	
}
