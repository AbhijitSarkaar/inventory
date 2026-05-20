
package com.microservices.order_service.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long productId;
    private String productName;
    private String productDescription;
    private String productSku;
    private BigDecimal price;

}
