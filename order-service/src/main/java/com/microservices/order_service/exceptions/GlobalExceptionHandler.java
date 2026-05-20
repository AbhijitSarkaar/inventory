package com.microservices.order_service.exceptions;

import com.microservices.order_service.exceptions.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIResponse> runtimeExceptionHandler(RuntimeException e) {
        return new ResponseEntity<>(
                new APIResponse(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
