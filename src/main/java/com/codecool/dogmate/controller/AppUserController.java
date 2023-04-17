package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.repository.AppUserRepository;
import com.codecool.dogmate.service.AppUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/app-users")
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserController(AppUserService appUserService, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping()
    public List<AppUserDto> getAllAppUsers() {return appUserService.getAppUsers();}

    @GetMapping(params = {"page", "size", "sort"})
    public List<AppUserDto> getAllUserWithPageable(Pageable pageable) {
        return appUserService.getAppUsers(pageable);
    }

    @GetMapping("/{id}")
    public AppUserDto getAppUserByUserId(@PathVariable Integer id) {
        return appUserService.getAppUserById(id);
    }

    @GetMapping(params= {"name"})
    public List<AppUserDto> getAppUserByName(@RequestParam String name) {
        return appUserService.getAppUserByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        appUserService.deleteAppUserData(id);
    }

}

