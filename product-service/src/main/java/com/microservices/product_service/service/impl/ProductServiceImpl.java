package com.microservices.product_service.service.impl;

import com.microservices.product_service.model.Product;
import com.microservices.product_service.payload.ProductDTO;
import com.microservices.product_service.payload.ProductRequestDTO;
import com.microservices.product_service.repository.ProductRepository;
import com.microservices.product_service.service.ProductService;
import com.microservices.product_service.util.DTOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DTOBuilder dtoBuilder;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(item -> dtoBuilder.productDtoBuilder(item))
                .toList();
    }

    @Override
    public ProductDTO createProduct(ProductRequestDTO productRequestDto) {
        Product product = new Product();

        product.setProductName(productRequestDto.getProductName());
        product.setProductDescription(productRequestDto.getProductDescription());
        product.setProductSku(productRequestDto.getProductSku());
        product.setPrice(productRequestDto.getPrice());

        product = productRepository.save(product);
        return dtoBuilder.productDtoBuilder(product);

    }
}
