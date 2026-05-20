package com.microservices.order_service.controller;

import com.microservices.order_service.payload.OrderDTO;
import com.microservices.order_service.payload.OrderRequestDTO;
import com.microservices.order_service.service.OrderService;
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
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> create(
            @Valid @RequestBody OrderRequestDTO orderRequestDto
    ) {
        return new ResponseEntity<>(
                orderService.createOrder(orderRequestDto),
                HttpStatus.CREATED
        );
    }

}
