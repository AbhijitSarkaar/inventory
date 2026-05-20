package com.microservices.user_service.service;

import com.microservices.user_service.exceptions.CustomResponse;
import com.microservices.user_service.payload.LoginRequestDTO;
import com.microservices.user_service.payload.UserDTO;
import com.microservices.user_service.payload.UserRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserDTO register(@Valid UserRequestDTO userRequestDto);

    ResponseCookie login(@Valid LoginRequestDTO loginRequestDto);

    ResponseCookie logout();

    ResponseEntity<?> details(HttpServletRequest httpServletRequest);
}




