package com.example.jeeHamlaoui.controller;

import com.example.jeeHamlaoui.model.User;
import com.example.jeeHamlaoui.model.dto.LoginRequest;
import com.example.jeeHamlaoui.model.dto.LoginResponse;
import com.example.jeeHamlaoui.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody User user) {
        LoginResponse response = authService.register(user);
        return ResponseEntity.ok(response);
    }
} 