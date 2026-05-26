
package com.microservices.inventory_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "skus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sku {
    @Id
    @Column(name = "sku_id")
    @NotNull
    @Length(min = 8)
    private String skuId;

    @NotNull
    @Column(name = "quantity")
    @Min(0)
    private Integer quantity;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}

