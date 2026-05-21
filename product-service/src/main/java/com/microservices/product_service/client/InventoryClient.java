package com.microservices.product_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "INVENTORY-SERVICE",
        url = "${inventory.service.url}"
)
public interface InventoryClient {
    @GetMapping("/api/inventory/{skuId}")
    public Boolean find(@PathVariable("skuId") String skuId);
}

