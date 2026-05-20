package com.microservices.product_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Column(name = "product_description")
    private String productDescription;

    @NotNull
    @Column(name = "sku")
    private String productSku;

    @NotNull
    @Column(precision = 10, scale = 2, name = "price")
    private BigDecimal price;

    public Product(String productName, String productDescription, String productSku, BigDecimal price) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productSku = productSku;
        this.price = price;
    }
}
