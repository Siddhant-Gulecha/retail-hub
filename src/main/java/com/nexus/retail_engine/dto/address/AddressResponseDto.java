package com.nexus.retail_engine.dto.address;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {
    private Long id;
    private String addressLine;
    private String city;
    private String state;
    private String pincode;
}
