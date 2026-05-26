package com.microservices.inventory_service.service;

import com.microservices.inventory_service.exception.response.CustomResponse;
import com.microservices.inventory_service.model.Sku;
import com.microservices.inventory_service.payload.SkuRequestDTO;
import jakarta.validation.Valid;

public interface InventoryService {
    CustomResponse addStock(@Valid SkuRequestDTO skuRequestDto);

    CustomResponse reduceStock(String skuId, Integer quantity);

    Sku find(String skuId);

    Sku checkAvailability(String skuId, Integer quantity);
}





