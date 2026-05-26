package com.microservices.user_service.controller;

import com.microservices.user_service.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/auth/verify")
    public ResponseEntity<?> verify(HttpServletRequest httpServletRequest) {
        return authService.verify(httpServletRequest);
    }
}
