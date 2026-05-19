package com.microservices.product_service.util;

import com.microservices.product_service.model.Product;
import com.microservices.product_service.payload.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class DTOBuilder {
    public ProductDTO productDtoBuilder(Product product) {
        ProductDTO productDto = new ProductDTO();

        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setProductSku(product.getProductSku());
        productDto.setPrice(product.getPrice());

        return productDto;
    }
}
