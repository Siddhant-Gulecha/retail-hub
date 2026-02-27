package com.nexus.retail_engine.security;

import com.nexus.retail_engine.dto.LoginRequestDto;
import com.nexus.retail_engine.dto.LoginResponseDto;
import com.nexus.retail_engine.dto.SignupRequestDto;
import com.nexus.retail_engine.dto.SignupResponsetDto;
import com.nexus.retail_engine.entity.User;
import com.nexus.retail_engine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager; // bean from AppConfig
    private final AuthUtil authUtil;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception{

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );// delegates responsibility to authenticate to AuthenticationManager

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }

    public SignupResponsetDto signup(SignupRequestDto signupRequestDto) throws Exception {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if(user!=null){
            throw new Exception("User already exists");
        }
        user = User.builder()
                        .username(signupRequestDto.getUsername())
                        .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                        .build();
        user = userRepository.save(user);
        String token = authUtil.generateAccessToken(user);
        return new SignupResponsetDto(token, user.getId());
    }


}
