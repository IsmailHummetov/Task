package com.example.transaction.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.UUID;

@Component
public class JwtDecoder {
    @Value("${my.secret.key}")
    private String SECRET_KEY;

    private SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public UUID extractUUID(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        Claims claims = extractAllClaims(jwt);
        UUID uuid = null;
        try {
            uuid = UUID.fromString(claims.get("id", String.class));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID string: " + e.getMessage());
        }
        return uuid;
    }
}
