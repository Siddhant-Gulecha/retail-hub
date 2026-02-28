package com.nexus.retail_engine.dto.address;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAddressesResponseDto {
    List<AddressDto> addresses;
}
