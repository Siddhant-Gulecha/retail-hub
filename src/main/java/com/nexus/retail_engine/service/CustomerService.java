package com.nexus.retail_engine.service;

import com.nexus.retail_engine.dto.profile.RegisterAsCustomerRequestDto;
import com.nexus.retail_engine.entity.Customer;
import com.nexus.retail_engine.entity.Seller;
import com.nexus.retail_engine.entity.User;
import com.nexus.retail_engine.entity.enums.RoleType;
import com.nexus.retail_engine.repository.CustomerRepository;
import com.nexus.retail_engine.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Transactional
    public void registerAsCustomer(RegisterAsCustomerRequestDto registerAsCustomerRequestDto) throws Exception {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.getReferenceById(principal.getId());

        if(user.getRoles().contains(RoleType.CUSTOMER)){
            throw new Exception("you are already a customer");
        }
        Customer customer = Customer.builder()
                .firstName(registerAsCustomerRequestDto.getFirstName())
                .lastName(registerAsCustomerRequestDto.getLastName())
                .phoneNumber(registerAsCustomerRequestDto.getPhoneNumber())
                .user(user)
                .build();
        customerRepository.save(customer);
        user.getRoles().add(RoleType.CUSTOMER);
        userRepository.save(user);
    }
}
