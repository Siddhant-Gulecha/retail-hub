package com.nexus.retail_engine.controller;

import com.nexus.retail_engine.dto.profile.RegisterAsCustomerRequestDto;
import com.nexus.retail_engine.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor
@RequestMapping("/customer")
@PreAuthorize("hasRole('CUSTOMER') || hasRole('ADMIN')")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/registerAsCustomer")
    public ResponseEntity<Void> registerAsCustomer(@RequestBody RegisterAsCustomerRequestDto registerAsCustomerRequestDto) throws Exception {
        customerService.registerAsCustomer(registerAsCustomerRequestDto);
        return ResponseEntity.noContent().build();
    }
}
