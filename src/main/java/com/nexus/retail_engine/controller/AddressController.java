package com.nexus.retail_engine.controller;

import com.nexus.retail_engine.dto.address.AddressCreateRequestDto;
import com.nexus.retail_engine.dto.address.AddressCreateResponseDto;
import com.nexus.retail_engine.dto.address.AddressDto;
import com.nexus.retail_engine.dto.address.GetAllAddressesResponseDto;
import com.nexus.retail_engine.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/all")
    public GetAllAddressesResponseDto getAllAddresses(){
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public AddressDto getAddress(@PathVariable Long id){
        return addressService.getAddressByid(id);
    }

    @PostMapping("/create")
    public AddressCreateResponseDto createAddress(@RequestBody @Valid AddressCreateRequestDto addressCreateRequestDto){
        return addressService.createAddress(addressCreateRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        addressService.removeAddress(id);
        return ResponseEntity.noContent().build();
    }

}
