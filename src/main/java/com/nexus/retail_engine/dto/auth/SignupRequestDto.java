package com.nexus.retail_engine.dto.auth;


import com.nexus.retail_engine.entity.enums.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private List<RoleType> roles;
}
