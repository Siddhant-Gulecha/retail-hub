package com.nexus.retail_engine.security;

import com.nexus.retail_engine.entity.User;
import com.nexus.retail_engine.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }
}
