package com.example.demo.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")

public class DemoController {
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is public.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userEndpoint() {
        return "This is user-protected.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "This is admin-only.";
    }

    @GetMapping("/me")
    public String me(Principal principal) {
        return "Hello, " + principal.getName();
    }
}
