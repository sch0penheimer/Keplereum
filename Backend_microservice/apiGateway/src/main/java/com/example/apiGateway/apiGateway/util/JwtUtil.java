package com.example.apiGateway.apiGateway.util;

import com.example.apiGateway.apiGateway.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token) {
        String jwtkey = "d8d34f879600193566e15f11d780ea015707c77dd906bd67c7de5d9abc8d6eb35d70755bfefe7d8d73b584eb908b36376a76e52baae659ab8539914836b156d844a17f830b7075958b9fc7ce09d150ecf5f92f2bfcbdc963b8c722eed7f94def3f992a2895aa83a0ec3e6e5ce7b910f5726f58098da7d197f030260d6cde415c108acd62f2af44538fe3114ca6f8827d544efa5ff2b7c2181cd8c0999c04d86ef85c41f1166ff74fc8c8aadef246ed43aeaf009549b157de7a574f7d402e99d36a9ebdc0cb3ed474961278150f553c2d688c89a081fc77e8bd2b3a5e306407ed1f30781f593a7bc7042f9fca536b2918f08e6d01729bf1ad890dc129e6c26a1a";
        byte[] keyBytes = jwtkey.getBytes(StandardCharsets.UTF_8);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        String[] parts = token.split("\\.");
        if (parts.length >= 1) {
            String headerJson = new String(Base64.getDecoder().decode(parts[0]));
            log.info("Token header: {}", headerJson);
        }
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            log.info("Token claims: {}", claims);
            return true;
        } catch (Exception e) {
            log.error("Token validation error: {}", e.getMessage());
            return false;
        }
    }

} 