package com.codecool.dogmate.dto.userrole;


import java.time.LocalDateTime;

public record UserRoleDto(

        Integer id,
        String name,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive
){
}
