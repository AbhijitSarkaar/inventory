package com.microservices.user_service.exceptions.handler;

import com.microservices.user_service.exceptions.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomResponse> runtimeExceptionHandler(RuntimeException e) {
        return new ResponseEntity<>(
                new CustomResponse(e.getMessage()),
                HttpStatus.OK
        );
    }
}
