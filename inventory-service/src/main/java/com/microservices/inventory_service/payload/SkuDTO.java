package com.microservices.inventory_service.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuDTO {
    @NotNull
    @Length(min = 8)
    private String skuId;

    @NotNull
    @Min(0)
    private Integer quantity;
}
