package org.suhodo.cardatabase.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;
import org.suhodo.cardatabase.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;                        // Jwt 발급/검증
    private final AuthenticationManager authenticationManager;  // Spring Security인증
}