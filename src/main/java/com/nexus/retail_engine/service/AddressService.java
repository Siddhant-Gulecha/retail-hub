package com.nexus.retail_engine.service;


import com.nexus.retail_engine.dto.address.*;
import com.nexus.retail_engine.entity.Address;
import com.nexus.retail_engine.entity.User;
import com.nexus.retail_engine.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;


    // READ ALL

    @Transactional
    public GetAllAddressesResponseDto getAllAddresses(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<AddressResponseDto> addresses = addressRepository.findByUserId(user.getId())
                .stream()
                .map(a -> modelMapper.map(a, AddressResponseDto.class))
                .toList();

        return new GetAllAddressesResponseDto(addresses);
    }


    // READ ONE

    @Transactional
    public AddressResponseDto getAddressByid(Long id) throws Exception {
        Address address = addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(address.getUser().getId() != id){
            throw new Exception("not your address to delete!");
        }
        AddressResponseDto addressDto = modelMapper.map(addressRepository.findById(id), AddressResponseDto.class);
        return addressDto;
    }


    // CREATE

    @Transactional
    public AddressCreateResponseDto createAddress(AddressCreateRequestDto addressCreateRequestDto){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Address address = Address.builder()
                .user(user)
                .addressLine(addressCreateRequestDto.getAddressLine())
                .city(addressCreateRequestDto.getCity())
                .state(addressCreateRequestDto.getState())
                .pincode(addressCreateRequestDto.getPincode())
                .build();
        address = addressRepository.save(address);
        return new AddressCreateResponseDto(address.getId());
    }


    // UPDATE

    @Transactional
    public AddressResponseDto updateAddress(Long id, AddressUpdateRequestDto addressUpdateRequestDto) throws AccessDeniedException {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!existingAddress.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You do not have permission to update this address");
        }

        modelMapper.map(addressUpdateRequestDto, existingAddress); // maps only non-null fields from updateDto

        Address updatedAddress = addressRepository.save(existingAddress);
        return modelMapper.map(updatedAddress, AddressResponseDto.class);
    }


    // DELETE

    @Transactional
    public void removeAddress(Long id) throws Exception {
        Address address = addressRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Address with ID " + id + " not found"));

        if (address.getUser().getId() != id) {
            throw new Exception("not your address to delete!");
        }

        addressRepository.deleteById(id);
    }
}
