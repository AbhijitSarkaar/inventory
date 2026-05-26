
package com.microservices.order_service.client;

import com.microservices.order_service.external.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "INVENTORY-SERVICE"
//        url = "${inventory.service.url}"
)
public interface InventoryClient {

    // check sku stock
    @GetMapping("/api/inventories/availability/{skuId}")
    public Sku availability(@PathVariable("skuId") String skuId, @RequestParam Integer quantity);

    // reduce sku stock
    @PutMapping("/api/inventories/{skuId}")
    public void reduce(@PathVariable("skuId") String skuId, @RequestParam Integer quantity);

}