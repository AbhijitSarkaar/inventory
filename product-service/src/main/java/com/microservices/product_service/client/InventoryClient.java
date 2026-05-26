package com.microservices.product_service.client;

import com.microservices.product_service.external.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "INVENTORY-SERVICE"
//        url = "${inventory.service.url}"
)
public interface InventoryClient {
    @GetMapping("/api/inventories/{skuId}")
    public Sku find(@PathVariable("skuId") String skuId);
}

