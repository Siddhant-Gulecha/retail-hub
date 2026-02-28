package com.nexus.retail_engine.controller;

import com.nexus.retail_engine.dto.product.ListProductsResponseDto;
import com.nexus.retail_engine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public ListProductsResponseDto listProducts(){
        return productService.listProducts();
    }

}
