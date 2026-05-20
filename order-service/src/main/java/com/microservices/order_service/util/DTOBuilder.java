package com.microservices.order_service.util;

import com.microservices.order_service.model.Order;
import com.microservices.order_service.model.OrderItem;
import com.microservices.order_service.payload.OrderDTO;
import com.microservices.order_service.payload.OrderItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DTOBuilder {
    public OrderDTO orderDtoBuilder(Order order) {
        OrderDTO orderDto = new OrderDTO();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setTotalPrice(order.getTotalPrice());
        List<OrderItem> orderItems = order.getOrderItems();
        if(orderItems != null) {
            orderDto.setOrderItems(
                    order.getOrderItems()
                            .stream()
                            .map(item -> {
                                OrderItemDTO orderItem = new OrderItemDTO();
                                orderItem.setProductId(item.getProductId());
                                orderItem.setQuantity(item.getQuantity());
                                return orderItem;
                            })
                            .toList()
            );
        }
        return orderDto;
    }
}
