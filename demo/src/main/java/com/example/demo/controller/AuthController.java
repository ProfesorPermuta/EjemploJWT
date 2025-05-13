package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jwt.JwtUtil;


@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String user) {
        // mock login - assign admin role to "admin", user to everyone else
        List<String> roles = user.equals("admin") ? List.of("ROLE_ADMIN") : List.of("ROLE_USER");
        String token = jwtUtil.generateToken(user, roles, 1);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
