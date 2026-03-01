package com.nexus.retail_engine.service;


import com.nexus.retail_engine.dto.product.ListProductsResponseDto;
import com.nexus.retail_engine.dto.product.ProductCreateRequestDto;
import com.nexus.retail_engine.dto.product.ProductResponseDto;
import com.nexus.retail_engine.entity.Product;
import com.nexus.retail_engine.entity.Seller;
import com.nexus.retail_engine.entity.User;
import com.nexus.retail_engine.repository.ProductRepository;
import com.nexus.retail_engine.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final SellerRepository sellerRepository;

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

    public ProductResponseDto getProduct(Long id){
        ProductResponseDto product = modelMapper.map(productRepository.findById(id), ProductResponseDto.class);
        return product;
    }

    public Long createProduct(ProductCreateRequestDto productCreateRequestDto) throws AccessDeniedException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(()-> new AccessDeniedException("only sellers are allowed to create product"));

        Product product = Product.builder()
                .name(productCreateRequestDto.getName())
                .description(productCreateRequestDto.getDescription())
                .price(productCreateRequestDto.getPrice())
                .currency(productCreateRequestDto.getCurrency())
                .seller(seller)
                .build();

        product = productRepository.save(product);
        return product.getId();
    }


}
