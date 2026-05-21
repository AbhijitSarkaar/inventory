package com.microservices.inventory_service.repository;

import com.microservices.inventory_service.model.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Sku, String> {
}
