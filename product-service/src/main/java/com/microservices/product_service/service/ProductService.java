package com.microservices.product_service.service;

import com.microservices.product_service.payload.ProductDTO;
import com.microservices.product_service.payload.ProductRequestDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(@Valid ProductRequestDTO productRequestDto);
}

