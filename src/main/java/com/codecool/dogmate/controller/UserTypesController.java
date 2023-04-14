package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.usertype.NewUserTypeDto;
import com.codecool.dogmate.dto.usertype.UpdateUserTypeDto;
import com.codecool.dogmate.dto.usertype.UserTypeDto;
import com.codecool.dogmate.service.UserTypesService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user-types")
public class UserTypesController {

    private final UserTypesService userTypesService;

    public UserTypesController(UserTypesService userTypesService) {
        this.userTypesService = userTypesService;
    }


    @GetMapping()
    public List<UserTypeDto> getAllUserTypes() {return userTypesService.getUserTypes();}


    @GetMapping(params = {"page", "size", "sort"})
    public List<UserTypeDto> getAllUserTypesWithPageable(Pageable pageable) {
        return userTypesService.getUserTypes(pageable);
    }

    @GetMapping("/{id}")
    public UserTypeDto getAppUserTypeByUserTypeId(@PathVariable Integer id) {
        return userTypesService.getUserTypeById(id);
    }

    @PostMapping("/add")
    public UserTypeDto newUserType(@RequestBody @Valid NewUserTypeDto userTypeDto) {
        return userTypesService.createUserType(userTypeDto);
    }
    @PutMapping("/update/{id}")
    public void updateUserType(@RequestBody @Valid UpdateUserTypeDto userType) {
        userTypesService.updateUserType(userType);
    }

    @PutMapping("/archive/{id}")
    public void archiveUserType(@PathVariable Integer id) {
        userTypesService.archiveUserType(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserType(@PathVariable Integer id) {
        userTypesService.deleteUserTypelData(id);
    }



}

