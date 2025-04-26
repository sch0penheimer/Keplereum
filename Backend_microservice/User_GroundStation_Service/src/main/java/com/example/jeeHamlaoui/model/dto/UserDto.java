package com.example.jeeHamlaoui.model.dto;

import com.example.jeeHamlaoui.model.enums.UserStatus;
import java.time.Instant;

public class UserDto {

    private Long id;
    private String userName;
    private String email;
    private UserStatus status;
    private Instant createdAt;

    // Constructors
    public UserDto() {}

    public UserDto(Long id, String userName, String email, UserStatus status, Instant createdAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
