package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.userprivilege.NewUserPrivilegeDto;
import com.codecool.dogmate.dto.userprivilege.UpdateUserPrivilegeDto;
import com.codecool.dogmate.dto.userprivilege.UserPrivilegeDto;
import com.codecool.dogmate.service.UserPrivilegesService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user-privileges")
public class UserPrivilegeController {

    private final UserPrivilegesService userPrivilegesService;

    public UserPrivilegeController(UserPrivilegesService userPrivilegesService) {
        this.userPrivilegesService = userPrivilegesService;
    }

    @GetMapping()
    public List<UserPrivilegeDto> getAllUserRoles() {return userPrivilegesService.getUserPrivilege();}


    @GetMapping(params = {"page", "size", "sort"})
    public List<UserPrivilegeDto> getAllUserPrivilegesWithPageable(Pageable pageable) {
        return userPrivilegesService.getUserPrivilege(pageable);
    }

    @GetMapping("/{id}")
    public UserPrivilegeDto getAppUserPrivilegeByUserTypeId(@PathVariable Integer id) {
        return userPrivilegesService.getUserPrivilegeById(id);
    }

    @PostMapping()
    public UserPrivilegeDto newUserPrivilege(@RequestBody @Valid NewUserPrivilegeDto userPrivilegeDto) {
        return userPrivilegesService.createUserPrivilege(userPrivilegeDto);
    }
    @PutMapping(params={"update"})
    public UserPrivilegeDto updateUserPrivilege(@RequestBody @Valid UpdateUserPrivilegeDto userPrivilege) {
        return userPrivilegesService.updateUserPrivilege(userPrivilege);
    }

    @PutMapping(value="/{id}", params={"archive"})
    public void archiveUserPrivilege(@PathVariable Integer id) {
        userPrivilegesService.archiveUserPrivilege(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserPrivilege(@PathVariable Integer id) {
        userPrivilegesService.deleteUserPrivilegeData(id);
    }



}

