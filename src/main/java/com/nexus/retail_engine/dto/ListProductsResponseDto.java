package com.nexus.retail_engine.dto;


import com.nexus.retail_engine.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListProductsResponseDto {
    List<ProductResponseDto> productList;
}
