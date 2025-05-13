package com.example.demo.jwt;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String secret = "yourSecretKey@1234Tengoquesermaslargo010101010101yourSecretKey@1234Tengoquesermaslargo010101010101yourSecretKey@1234Tengoquesermaslargo010101010101yourSecretKey@1234Tengoquesermaslargo010101010101yourSecretKey@1234Tengoquesermaslargo010101010101";
    private final long expirationMs = 3600000;

    public String generateToken(String username, List<String> roles, int id) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .claim("id", id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return extractClaims(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        return extractClaims(token).get("roles", List.class);
    }

    public Integer getId(String token) {
        return extractClaims(token).get("id", Integer.class);

    }
}