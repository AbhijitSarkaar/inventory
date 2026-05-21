
package com.microservices.order_service.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sku {
    private String skuId;
    private Integer quantity;
}

