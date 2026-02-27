package com.nexus.retail_engine.service;


import com.nexus.retail_engine.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
}
