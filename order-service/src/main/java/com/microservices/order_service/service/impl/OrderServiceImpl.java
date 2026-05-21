
package com.microservices.order_service.service.impl;

import com.microservices.order_service.client.InventoryClient;
import com.microservices.order_service.client.ProductClient;
import com.microservices.order_service.external.Product;
import com.microservices.order_service.external.Sku;
import com.microservices.order_service.model.Order;
import com.microservices.order_service.model.OrderItem;
import com.microservices.order_service.payload.OrderDTO;
import com.microservices.order_service.payload.OrderItemDTO;
import com.microservices.order_service.payload.OrderRequestDTO;
import com.microservices.order_service.repository.OrderItemRepository;
import com.microservices.order_service.repository.OrderRepository;
import com.microservices.order_service.service.OrderService;
import com.microservices.order_service.util.DTOBuilder;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductClient productClient;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    DTOBuilder dtoBuilder;

    @Autowired
    InventoryClient inventoryClient;

    @Transactional
    @Override
    public OrderDTO createOrder(OrderRequestDTO orderRequestDto) {

        Order order = new Order();

        BigDecimal totalPrice = new BigDecimal("0.00");
        List<OrderItemDTO> orderItemDtos= orderRequestDto.getOrderItems();
        List<OrderItem> orderItems = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for(OrderItemDTO item: orderItemDtos) {
            Product product = productClient.details(item.getProductId());
            Sku sku = inventoryClient.availability(product.getProductSku(), item.getQuantity());
            if(sku.getSkuId() == null) {
                throw new RuntimeException("Product with id " + product.getProductId() + " unavailable");
            } else {
                map.put(product.getProductSku(), item.getQuantity());
            }

            BigDecimal num = product.getPrice().multiply(
                    new BigDecimal(item.getQuantity())
            );
            totalPrice = totalPrice.add(num);

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(product.getPrice());
            orderItems.add(orderItem);
        }

        order.setTotalPrice(totalPrice);
        order.setUpdatedAt(LocalDateTime.now());

        // to be updated
        order.setUserId(1L);

        order = orderRepository.save(order);
        for(OrderItem orderItem: orderItems) {
            orderItem.setOrder(order);
        }
        orderItemRepository.saveAll(orderItems);
        order.setOrderItems(orderItems);
        OrderDTO responseDto = dtoBuilder.orderDtoBuilder(order);

        // reduce inventory stock of ordered items
        Set<String> keys = map.keySet();
        for(String key: keys) {
            inventoryClient.reduce(key, map.get(key));
        }

        return responseDto;
    }
}

