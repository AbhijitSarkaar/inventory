package com.microservices.user_service.controller;

import com.microservices.user_service.exceptions.CustomResponse;
import com.microservices.user_service.payload.LoginRequestDTO;
import com.microservices.user_service.payload.UserDTO;
import com.microservices.user_service.payload.UserRequestDTO;
import com.microservices.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
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
        return new ResponseEntity<>(
                userService.login(loginRequestDto),
                HttpStatus.OK
        );
    }

    //logout

}
