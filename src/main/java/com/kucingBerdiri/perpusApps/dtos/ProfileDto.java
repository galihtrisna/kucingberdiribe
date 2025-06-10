package com.kucingBerdiri.perpusApps.dtos;

import java.time.LocalDateTime;

public class ProfileDto {
    private String username;
    private String fullName;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    // Constructors
    public ProfileDto() {}

    public ProfileDto(String username, String fullName, String role,
                           LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    // Getters and setters
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