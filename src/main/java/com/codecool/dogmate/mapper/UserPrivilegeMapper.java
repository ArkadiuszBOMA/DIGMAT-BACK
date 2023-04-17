package com.codecool.dogmate.mapper;

import com.codecool.dogmate.dto.userprivilege.NewUserPrivilegeDto;
import com.codecool.dogmate.dto.userprivilege.UserPrivilegeDto;
import com.codecool.dogmate.entity.UserPrivilege;
import org.springframework.stereotype.Component;

@Component
public class UserPrivilegeMapper {

    public UserPrivilege mapNewUserRoleDtoToEntity(NewUserPrivilegeDto dto) {return new UserPrivilege(dto.name());}

    public UserPrivilegeDto mapEntityToUserRoleDto(UserPrivilege entity) {
        return new UserPrivilegeDto(
                entity.getId(),
                entity.getName(),
                entity.getDate_create(),
                entity.getDate_modify(),
                entity.getDate_archive(),
                entity.getArchive()
        );
    }
}
