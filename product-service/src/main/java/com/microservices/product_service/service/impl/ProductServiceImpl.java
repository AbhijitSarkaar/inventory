package com.microservices.product_service.service.impl;

import com.microservices.product_service.client.InventoryClient;
import com.microservices.product_service.exception.exceptions.ResourceNotFoundException;
import com.microservices.product_service.exception.response.CustomResponse;
import com.microservices.product_service.model.Product;
import com.microservices.product_service.payload.ProductDTO;
import com.microservices.product_service.payload.ProductRequestDTO;
import com.microservices.product_service.repository.ProductRepository;
import com.microservices.product_service.service.ProductService;
import com.microservices.product_service.util.DTOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DTOBuilder dtoBuilder;

    @Autowired
    InventoryClient inventoryClient;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(item -> dtoBuilder.productDtoBuilder(item))
                .toList();
    }

    @Override
    public ProductDTO createProduct(ProductRequestDTO productRequestDto) {
        try {
            inventoryClient.find(productRequestDto.getProductSku());
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid Sku provided");
        }

        Product product = new Product();

        product.setProductName(productRequestDto.getProductName());
        product.setProductDescription(productRequestDto.getProductDescription());
        product.setProductSku(productRequestDto.getProductSku());
        product.setPrice(productRequestDto.getPrice());

        product = productRepository.save(product);
        return dtoBuilder.productDtoBuilder(product);
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = getById(productId);
        return dtoBuilder.productDtoBuilder(product);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductRequestDTO productRequestDto) {
        Product product = getById(productId);

        product.setProductName(productRequestDto.getProductName());
        product.setProductDescription(productRequestDto.getProductDescription());
        product.setProductSku(productRequestDto.getProductSku());
        product.setPrice(productRequestDto.getPrice());
        product.setProductId(productId);

        product = productRepository.save(product);

        return dtoBuilder.productDtoBuilder(product);
    }

    @Override
    public CustomResponse deleteProduct(Long productId) {
        getById(productId);
        productRepository.deleteById(productId);
        return new CustomResponse("Product with " + productId + " deleted");
    }

    Product getById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product", "productId", productId.toString())
                );
    }
}
