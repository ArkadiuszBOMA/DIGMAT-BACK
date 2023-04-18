package com.codecool.dogmate.controller;

import com.codecool.dogmate.dto.userrole.NewUserRoleDto;
import com.codecool.dogmate.dto.userrole.UpdateUserRoleDto;
import com.codecool.dogmate.dto.userrole.UserRoleDto;
import com.codecool.dogmate.service.UserRolesService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user-roles")
public class UserRoleController {

    private final UserRolesService userRolesService;

    public UserRoleController(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }


    @GetMapping()
    public List<UserRoleDto> getAllUserRole() {return userRolesService.getUserRole();}


    @GetMapping(params = {"page", "size", "sort"})
    public List<UserRoleDto> getAllUserRolesWithPageable(Pageable pageable) {
        return userRolesService.getUserRoles(pageable);
    }

    @GetMapping("/{id}")
    public UserRoleDto getAppUserRoleByUserRoleId(@PathVariable Integer id) {
        return userRolesService.getUserRoleById(id);
    }

    @PostMapping()
    public UserRoleDto newUserRole(@RequestBody @Valid NewUserRoleDto userRoleDto) {
        return userRolesService.createUserRole(userRoleDto);
    }
    @PutMapping(params={"update"})
    public void updateUserRole(@RequestBody @Valid UpdateUserRoleDto userRole) {
        userRolesService.updateUserRole(userRole);
    }

    @PutMapping(value="/{id}", params={"archive"})
    public void archiveUserRole(@PathVariable Integer id) {
        userRolesService.archiveUserRole(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserRole(@PathVariable Integer id) {
        userRolesService.deleteUserRoleData(id);
    }



}

