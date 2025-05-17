package com.example.jeeHamlaoui.service;

import com.example.jeeHamlaoui.model.User;
import com.example.jeeHamlaoui.model.dto.LoginRequest;
import com.example.jeeHamlaoui.model.dto.LoginResponse;
import com.example.jeeHamlaoui.model.dto.UserDto;
import com.example.jeeHamlaoui.model.dto.UserMapper;
import com.example.jeeHamlaoui.model.enums.UserStatus;
import com.example.jeeHamlaoui.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret:your-256-bit-secret}")
    private String jwtSecret;

    @Value("${jwt.expiration:86400}")
    private long jwtExpiration;

    public AuthService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = generateToken(user);
        return new LoginResponse(token, userMapper.toDto(user));
    }

    public LoginResponse register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        User savedUser = userRepository.save(user);

        String token = generateToken(savedUser);
        return new LoginResponse(token, userMapper.toDto(savedUser));
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
} 