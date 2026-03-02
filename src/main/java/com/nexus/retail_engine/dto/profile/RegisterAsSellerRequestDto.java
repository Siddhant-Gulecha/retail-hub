package com.nexus.retail_engine.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterAsSellerRequestDto {

    @Size(max = 100)
    @NotBlank
    private String sellerName; // company name

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    @Size(max = 15)
    private String phoneNumber;

    @Size(max = 15)
    @NotBlank(message = "GSTIN is mandatory")
    @Pattern(
            regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$",
            message = "Invalid GSTIN format"
    )
    private String gstin;
}
