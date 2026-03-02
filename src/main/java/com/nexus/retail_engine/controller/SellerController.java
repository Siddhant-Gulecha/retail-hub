package com.nexus.retail_engine.controller;

import com.nexus.retail_engine.dto.profile.RegisterAsSellerRequestDto;
import com.nexus.retail_engine.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
@PreAuthorize("hasRole('SELLER') || hasRole('ADMIN')")
public class SellerController {
    private final SellerService sellerService;

    @PostMapping("/registerAsSeller")
    public ResponseEntity<Void> registerAsSeller(@RequestBody @Valid RegisterAsSellerRequestDto registerAsSellerRequestDto) throws Exception {
        sellerService.registerAsSeller(registerAsSellerRequestDto);
        return ResponseEntity.noContent().build();
    }

}
