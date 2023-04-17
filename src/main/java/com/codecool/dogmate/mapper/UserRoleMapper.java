package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.userrole.NewUserRoleDto;
import com.codecool.dogmate.dto.userrole.UserRoleDto;
import com.codecool.dogmate.entity.*;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

//    private final UserMapper userMapper;
//
//    public UserTypesMapper(UserMapper userMapper) {this.userMapper = userMapper; }

    public UserRole mapNewUserTypeDtoToEntity(NewUserRoleDto dto) {return new UserRole(dto.name());}

    public UserRoleDto mapEntityToUserTypeDto(UserRole entity) {
        return new UserRoleDto(
                entity.getId(),
                entity.getName(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive()
        );
    }
}
