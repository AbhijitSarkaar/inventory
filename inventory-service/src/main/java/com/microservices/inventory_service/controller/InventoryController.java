package com.microservices.inventory_service.controller;

import com.microservices.inventory_service.exception.response.CustomResponse;
import com.microservices.inventory_service.model.Sku;
import com.microservices.inventory_service.payload.SkuRequestDTO;
import com.microservices.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/inventories")
    public ResponseEntity<CustomResponse> add(
            @Valid @RequestBody SkuRequestDTO skuRequestDto
    ) {
        return new ResponseEntity<>(
                inventoryService.addStock(skuRequestDto),
                HttpStatus.OK
        );
    }

    @PutMapping("/inventories/{skuId}")
    public ResponseEntity<CustomResponse> reduce(
            @PathVariable("skuId") String skuId,
            @RequestParam Integer quantity
    ) {
        return new ResponseEntity<>(
                inventoryService.reduceStock(skuId, quantity),
                HttpStatus.OK
        );
    }

    @GetMapping("/inventories/{skuId}")
    public ResponseEntity<Sku> find(
            @PathVariable("skuId") String skuId
    ) {
        return new ResponseEntity<>(
                inventoryService.find(skuId),
                HttpStatus.OK

        );
    }

    @GetMapping("/inventories/availability/{skuId}")
    public ResponseEntity<?> availability(
            @PathVariable("skuId") String skuId,
            @RequestParam Integer quantity
    ) {
        return new ResponseEntity<Sku>(
                inventoryService.checkAvailability(skuId, quantity),
                HttpStatus.OK
        );
    }

}


