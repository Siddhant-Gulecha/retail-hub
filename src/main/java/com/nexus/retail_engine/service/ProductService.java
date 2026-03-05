package com.nexus.retail_engine.service;

import com.nexus.retail_engine.dto.product.ListProductsResponseDto;
import com.nexus.retail_engine.dto.product.ProductCreateRequestDto;
import com.nexus.retail_engine.dto.product.ProductResponseDto;
import com.nexus.retail_engine.dto.product.ProductUpdateRequestDto;
import com.nexus.retail_engine.entity.Product;
import com.nexus.retail_engine.entity.Seller;
import com.nexus.retail_engine.entity.User;
import com.nexus.retail_engine.repository.ProductRepository;
import com.nexus.retail_engine.repository.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
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


    // READ ALL

    public ListProductsResponseDto listProducts() {

        List<ProductResponseDto> productList = productRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProductResponseDto.class))
                .toList();

        return new ListProductsResponseDto(productList);
    }


    // READ ONE

    public ProductResponseDto getProduct(Long id){

        ProductResponseDto product = modelMapper.map(productRepository.findById(id), ProductResponseDto.class);
        return product;
    }


    // CREATE

    @Transactional
    @PreAuthorize("hasAuthority('product:create')")
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


    // UPDATE

    @Transactional
    @PreAuthorize("hasAuthority('product:update')")
    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto productUpdateRequestDto) throws AccessDeniedException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!existingProduct.getSeller().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You do not have permission to update this address");
        }

        modelMapper.map(productUpdateRequestDto, existingProduct); // maps only non-null fields from updateDto

        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductResponseDto.class);
    }


    // DELETE

    @Transactional
    @PreAuthorize("hasAuthority('product:delete')")
    public void deleteProduct(Long id) throws AccessDeniedException {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!existingProduct.getSeller().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You do not have permission to update this address");
        }

        productRepository.deleteById(id);
    }
}
