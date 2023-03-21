package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.animal.AnimalDto;
import com.codecool.dogmate.dto.appuser.AppUserDto;
import com.codecool.dogmate.dto.usertype.NewUserTypeDto;
import com.codecool.dogmate.dto.usertype.UserTypeDto;
import com.codecool.dogmate.dto.voivodeship.NewVoivodeshipDto;
import com.codecool.dogmate.dto.voivodeship.VoivodeshipDto;
import com.codecool.dogmate.service.UserTypesService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usertype")
public class UserTypesController {

    private final UserTypesService userTypesService;

    public UserTypesController(UserTypesService userTypesService) {
        this.userTypesService = userTypesService;
    }


    @GetMapping
    public List<UserTypeDto> getAllUserTypes() {return userTypesService.getUserTypes();}


    @GetMapping(params = {"page", "size", "sort"})
    public List<UserTypeDto> getAllUserTypesWithPageable(Pageable pageable) {
        return userTypesService.getUserTypes(pageable);
    }

    @GetMapping("/{id}")
    public UserTypeDto getAppUserTypeByUserTypeId(@PathVariable Integer id) {
        return userTypesService.getUserTypeById(id);
    }

    @PostMapping
    public UserTypeDto newUserType(@RequestBody NewUserTypeDto userTypeDto) {
        return userTypesService.createUserType(userTypeDto);
    }




}

