
package com.microservices.inventory_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}

