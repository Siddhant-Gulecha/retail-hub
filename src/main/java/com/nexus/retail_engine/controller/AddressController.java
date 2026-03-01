package com.nexus.retail_engine.controller;

import com.nexus.retail_engine.dto.address.*;
import com.nexus.retail_engine.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    // READ ALL OF USER

    @GetMapping("/all")
    public ResponseEntity<GetAllAddressesResponseDto> getAllAddresses(){
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    // READ BY ID

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddress(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(addressService.getAddressByid(id));
    }

    // CREATE

    @PostMapping("/create")
    public ResponseEntity<AddressCreateResponseDto> createAddress(@RequestBody @Valid AddressCreateRequestDto addressCreateRequestDto){
        return ResponseEntity.ok(addressService.createAddress(addressCreateRequestDto));
    }

    // UPDATE

    @PatchMapping("/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable Long id, @RequestBody @Valid AddressUpdateRequestDto addressUpdateRequestDto) throws AccessDeniedException {
        return ResponseEntity.ok(addressService.updateAddress(id, addressUpdateRequestDto));
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) throws Exception {
        addressService.removeAddress(id);
        return ResponseEntity.noContent().build();
    }

}
