package com.nexus.retail_engine.dto.product;


import com.nexus.retail_engine.entity.enums.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductUpdateRequestDto {

    @Size(max=50)
    private String name;

    @Size(max = 1000)
    private String description;

    @Min(0)
    private Double price;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
}
