package com.microservices.user_service.controller;

import com.microservices.user_service.exceptions.CustomResponse;
import com.microservices.user_service.payload.LoginRequestDTO;
import com.microservices.user_service.payload.UserDTO;
import com.microservices.user_service.payload.UserRequestDTO;
import com.microservices.user_service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    //register
    @PostMapping("/users/register")
    public ResponseEntity<UserDTO> register(
            @Valid @RequestBody UserRequestDTO userRequestDto
    ) {
        return new ResponseEntity<>(
                userService.register(userRequestDto),
                HttpStatus.CREATED
        );
    }

    //login
    @PostMapping("/users/login")
    public ResponseEntity<CustomResponse> login(
            @Valid @RequestBody LoginRequestDTO loginRequestDto
            ) {
        ResponseCookie cookie = userService.login(loginRequestDto);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(
                new CustomResponse("logged in")
        );
    }

    //logout
    @PostMapping("/users/logout")
    public ResponseEntity<CustomResponse> logout() {
        ResponseCookie cookie = userService.logout();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(
                new CustomResponse("logged out")
        );
    }

    // get details in response headers
    @GetMapping("/users/details")
    public ResponseEntity<?> details(HttpServletRequest httpServletRequest) {
        return userService.details(httpServletRequest);
    }
}
