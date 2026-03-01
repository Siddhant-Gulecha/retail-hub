package com.nexus.retail_engine.controller;

import com.nexus.retail_engine.dto.product.ListProductsResponseDto;
import com.nexus.retail_engine.dto.product.ProductCreateRequestDto;
import com.nexus.retail_engine.dto.product.ProductResponseDto;
import com.nexus.retail_engine.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ListProductsResponseDto> listProducts(){
        return ResponseEntity.ok(productService.listProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("/create")
    public ResponseEntity<Long> createProduct(@RequestBody ProductCreateRequestDto productCreateRequestDto) throws AccessDeniedException {
        return ResponseEntity.ok(productService.createProduct(productCreateRequestDto));
    }





}
