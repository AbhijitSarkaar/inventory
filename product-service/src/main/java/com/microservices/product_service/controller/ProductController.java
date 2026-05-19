
package com.microservices.product_service.controller;

import com.microservices.product_service.payload.ProductDTO;
import com.microservices.product_service.payload.ProductRequestDTO;
import com.microservices.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> list() {
        return new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK
        );
    }

    @PostMapping("/products")
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

//    @GetMapping("/products/{productId}")
//    public ResponseEntity<ProductDTO> details(
//            @PathVariable("productId") String productId
//    ) {
//
//    }

//    @PutMapping("/products/{productId}")
//    @DeleteMapping("/products/{productId}")


}
