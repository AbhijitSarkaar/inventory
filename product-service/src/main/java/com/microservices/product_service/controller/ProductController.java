
package com.microservices.product_service.controller;

import com.microservices.product_service.exception.response.CustomResponse;
import com.microservices.product_service.payload.ProductDTO;
import com.microservices.product_service.payload.ProductRequestDTO;
import com.microservices.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> list(Authentication authentication) {
        return new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK
        );
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> create(
            @Valid @RequestBody ProductRequestDTO productRequestDto
    ) {
        return new ResponseEntity<>(
                productService.createProduct(
                        productRequestDto
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> details(
            @PathVariable("productId") Long productId
    ) {
        return new ResponseEntity<>(
                productService.getProductById(productId),
                HttpStatus.OK
        );
    }

    @PutMapping("/products/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> update(
            @PathVariable("productId") Long productId,
            @Valid @RequestBody ProductRequestDTO productRequestDto
    ) {
        return new ResponseEntity<>(
                productService.updateProduct(productId, productRequestDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/products/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse> delete(
            @PathVariable("productId") Long productId
    ) {
        return new ResponseEntity<>(
                productService.deleteProduct(productId),
                HttpStatus.OK
        );
    }

}
