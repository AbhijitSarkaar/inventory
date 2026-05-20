package com.microservices.product_service.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    @NotNull
    private String productName;

    @NotNull
    private String productDescription;

    @NotNull
    private String productSku;

    @NotNull
    private BigDecimal price;
}
