package com.example.jeeHamlaoui.model;

import com.example.jeeHamlaoui.model.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;

import java.time.Instant;
import java.util.Objects;

@Builder
@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    private Long user_id;

    private String user_name;
    private String password;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne
    @JoinColumn(name = "groundStation_id")
    private GroundStation groundStation;

    private Instant created_at;

    public User() {
    }

    public User(Long user_id, String user_name, String password, String email, UserStatus status, Instant created_at) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.status = status;
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(user_id, user.user_id) && Objects.equals(user_name, user.user_name) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && status == user.status && Objects.equals(created_at, user.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, user_name, password, email, status, created_at);
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public Long getId() {
        return user_id;
    }
}
