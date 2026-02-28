package com.nexus.retail_engine.controller;

import com.nexus.retail_engine.dto.auth.LoginRequestDto;
import com.nexus.retail_engine.dto.auth.LoginResponseDto;
import com.nexus.retail_engine.dto.auth.SignupRequestDto;
import com.nexus.retail_engine.dto.auth.SignupResponsetDto;
import com.nexus.retail_engine.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) throws Exception{
        return authService.login(loginRequestDto);
    }

    @PostMapping("/signup")
    public SignupResponsetDto signup(@RequestBody SignupRequestDto signupRequestDto) throws Exception{
        return authService.signup(signupRequestDto);
    }

}
