package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.appuser.NewAppUserDto;
import com.codecool.dogmate.dto.auth.JwtTokenRequest;
import com.codecool.dogmate.dto.auth.JwtTokenResponse;
import com.codecool.dogmate.service.AppUserService;
import com.codecool.dogmate.service.JwtTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/app-users")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;
    private AppUserService appUserService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.appUserService = appUserService;
    }

    @PostMapping("/login")
    public JwtTokenResponse login(@Valid @RequestBody JwtTokenRequest jwtTokenRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(
                jwtTokenRequest.email(), jwtTokenRequest.password()
        );
        authenticationManager.authenticate(authentication);

        return new JwtTokenResponse(jwtTokenService.generateToken(jwtTokenRequest.email()));
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody NewAppUserDto newAppUserDto){
        appUserService.createAppUser(newAppUserDto);
    }
}
