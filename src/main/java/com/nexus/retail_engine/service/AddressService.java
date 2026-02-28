package com.nexus.retail_engine.service;


import com.nexus.retail_engine.dto.address.AddressCreateRequestDto;
import com.nexus.retail_engine.dto.address.AddressCreateResponseDto;
import com.nexus.retail_engine.dto.address.AddressDto;
import com.nexus.retail_engine.dto.address.GetAllAddressesResponseDto;
import com.nexus.retail_engine.entity.Address;
import com.nexus.retail_engine.entity.User;
import com.nexus.retail_engine.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public GetAllAddressesResponseDto getAllAddresses(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<AddressDto> addresses = addressRepository.findByUserId(user.getId())
                .stream()
                .map(a -> modelMapper.map(a, AddressDto.class))
                .toList();

        return new GetAllAddressesResponseDto(addresses);
    }

    public AddressDto getAddressByid(Long id){
        AddressDto address = modelMapper.map(addressRepository.findById(id), AddressDto.class);
        return address;
    }


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

    public void removeAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Address with ID " + id + " not found");
        }
        // TODO : add check if it is owned by user that wants to delete it
        addressRepository.deleteById(id);
    }

    // TODO : add update endpoint and service function
}
