package com.microservices.order_service.payload;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    private Long orderId;
    private BigDecimal totalPrice;
    private List<OrderItemDTO> orderItems;

}
