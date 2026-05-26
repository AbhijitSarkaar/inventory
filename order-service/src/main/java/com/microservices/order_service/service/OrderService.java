package com.microservices.order_service.service;

import com.microservices.order_service.payload.OrderDTO;
import com.microservices.order_service.payload.OrderRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public interface OrderService {
    OrderDTO createOrder(@Valid OrderRequestDTO orderRequestDto, HttpServletRequest httpServletRequest);
}


