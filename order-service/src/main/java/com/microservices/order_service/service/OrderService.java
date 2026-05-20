package com.microservices.order_service.service;

import com.microservices.order_service.payload.OrderDTO;
import com.microservices.order_service.payload.OrderRequestDTO;
import jakarta.validation.Valid;

public interface OrderService {
    OrderDTO createOrder(@Valid OrderRequestDTO orderRequestDto);
}


