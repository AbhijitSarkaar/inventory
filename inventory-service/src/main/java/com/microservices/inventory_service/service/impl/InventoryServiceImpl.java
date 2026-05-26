package com.microservices.inventory_service.service.impl;

import com.microservices.inventory_service.exception.response.CustomResponse;
import com.microservices.inventory_service.model.Sku;
import com.microservices.inventory_service.payload.SkuRequestDTO;
import com.microservices.inventory_service.repository.InventoryRepository;
import com.microservices.inventory_service.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public CustomResponse addStock(SkuRequestDTO skuRequestDto) {
        Sku sku = new Sku();

        try {
            sku = getById(skuRequestDto.getSkuId());
        } catch (RuntimeException e) {
            sku.setQuantity(0);
        }

        sku.setSkuId(skuRequestDto.getSkuId());
        sku.setQuantity(sku.getQuantity() + skuRequestDto.getQuantity());

        inventoryRepository.save(sku);

        return new CustomResponse("Sku updated");
    }

    @Override
    public CustomResponse reduceStock(String skuId, Integer quantity) {
        Sku sku = checkAvailability(skuId, quantity);
        sku.setQuantity(sku.getQuantity() - quantity);
        inventoryRepository.save(sku);
        return new CustomResponse("Updated");
    }

    @Override
    public Sku find(String skuId) {
        try {
            Sku sku = getById(skuId);
            return sku;
        } catch (RuntimeException e) {}

        return null;
    }

    Sku getById(String skuId) {
        return inventoryRepository.findById(skuId)
                .orElseThrow(() -> new RuntimeException("Sku with " + skuId + " not found"));
    }

    public Sku checkAvailability(String skuId, Integer quantity) {
        Sku sku = getById(skuId);
        if(sku.getQuantity() < quantity) {
            throw new RuntimeException("Product stock unavailable");
        }
        return sku;
    }

}
