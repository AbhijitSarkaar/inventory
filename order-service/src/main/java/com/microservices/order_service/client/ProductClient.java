package com.microservices.order_service.client;

import com.microservices.order_service.external.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "PRODUCT-SERVICE"
//        url = "${product.service.url}"
)
public interface ProductClient {
    @GetMapping("/api/products/{productId}")
    Product details(@PathVariable("productId") Long productId);
}
