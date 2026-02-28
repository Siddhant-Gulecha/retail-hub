package com.nexus.retail_engine.dto.address;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateRequestDto {

    @NotBlank(message = "Address line is required")
    @Size(max = 255)
    private String addressLine;

    @NotBlank(message = "City is required")
    @Size(max = 100)
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 100)
    private String state;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^[A-Z0-9 ]{3,10}$", message = "Invalid pincode format")
    private String pincode;

}
