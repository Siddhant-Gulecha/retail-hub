package com.nexus.retail_engine.service;


import com.nexus.retail_engine.dto.product.ListProductsResponseDto;
import com.nexus.retail_engine.dto.product.ProductResponseDto;
import com.nexus.retail_engine.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public void removeProduct(Long id){
        productRepository.deleteById(id);
    }

    public ListProductsResponseDto listProducts() {
        List<ProductResponseDto> productList = productRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProductResponseDto.class))
                .toList();

        return new ListProductsResponseDto(productList);
    }
}
