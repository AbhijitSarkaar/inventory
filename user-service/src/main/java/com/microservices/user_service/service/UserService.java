package com.microservices.user_service.service;

import com.microservices.user_service.exceptions.CustomResponse;
import com.microservices.user_service.payload.LoginRequestDTO;
import com.microservices.user_service.payload.UserDTO;
import com.microservices.user_service.payload.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;

public interface UserService {
    UserDTO register(@Valid UserRequestDTO userRequestDto);

    CustomResponse login(@Valid LoginRequestDTO loginRequestDto);
}

