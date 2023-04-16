package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.userrole.NewUserRoleDto;
import com.codecool.dogmate.dto.userrole.UserRoleDto;
import com.codecool.dogmate.entity.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    public UserRole mapNewUserRoleDtoToEntity(NewUserRoleDto dto) {return new UserRole(dto.name());}

    public UserRoleDto mapEntityToUserRoleDto(UserRole entity) {
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
