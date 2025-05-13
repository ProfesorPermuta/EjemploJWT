package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class TestController {
    
    @GetMapping("login")
    @PreAuthorize("hasRole('ADMIN')")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    

}
