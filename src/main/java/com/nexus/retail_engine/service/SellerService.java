package com.nexus.retail_engine.service;

import com.nexus.retail_engine.dto.profile.RegisterAsSellerRequestDto;
import com.nexus.retail_engine.entity.Seller;
import com.nexus.retail_engine.entity.User;
import com.nexus.retail_engine.entity.enums.RoleType;
import com.nexus.retail_engine.repository.SellerRepository;
import com.nexus.retail_engine.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;

    @Transactional
    public void registerAsSeller(RegisterAsSellerRequestDto registerAsSellerRequestDto) throws Exception {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.getReferenceById(principal.getId());

        if(user.getRoles().contains(RoleType.SELLER)){
            throw new Exception("you are already a seller");
        }
        Seller seller = Seller.builder()
                .sellerName(registerAsSellerRequestDto.getSellerName())
                .phoneNumber(registerAsSellerRequestDto.getPhoneNumber())
                .gstin(registerAsSellerRequestDto.getGstin())
                .user(user)
                .build();
        sellerRepository.save(seller);
        user.getRoles().add(RoleType.SELLER);
        userRepository.save(user);
    }
}
