package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.appuser.AppUserLoginDto;
import com.codecool.dogmate.dto.appuser.NewAppUserDto;
import com.codecool.dogmate.entity.AppUser;
import com.codecool.dogmate.repository.AppUserRepository;
import com.codecool.dogmate.service.AppUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appuser")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserController(AppUserService appUserService, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public List<AppUserDto> getAllAppUsers() {return appUserService.getAppUsers();}

    @GetMapping(params = {"page", "size", "sort"})
    public List<AppUserDto> getAllUserWithPageable(Pageable pageable) {
        return appUserService.getAppUsers(pageable);
    }

    @GetMapping("/id/{id}")
    public AppUserDto getAppUserByUserId(@PathVariable Integer id) {
        return appUserService.getAppUserById(id);
    }

    @GetMapping("/name/{name}")
    public List<AppUserDto> getAppUserByName(@PathVariable String name) {
        return appUserService.getAppUserByName(name);
    }

    @PostMapping("/register")
    public AppUserDto newAppUser(@RequestBody NewAppUserDto newAppUserDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newAppUserDto.password());
        newAppUserDto.setPassword(encodedPassword);
        appUserService.createAppUser(newAppUserDto);
        return appUserService.login(newAppUserDto.email(), newAppUserDto.password());
    }

    @PostMapping("/login")
    public AppUserDto loginUser(@RequestBody AppUserLoginDto appUserLoginDto) {
        Optional<AppUser> appUser = appUserRepository.findOneByEmail(appUserLoginDto.email());
        if(appUser.isEmpty() || !passwordEncoder.matches(appUserLoginDto.password(),appUser.get().getPassword())){
            return null;
        }
        return appUserService.login(appUserLoginDto.email(), appUserLoginDto.password());
    }

}

