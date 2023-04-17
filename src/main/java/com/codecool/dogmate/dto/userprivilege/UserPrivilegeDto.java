package com.codecool.dogmate.dto.userprivilege;


import java.time.LocalDateTime;

public record UserPrivilegeDto(

        Integer id,
        String name,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive
){
}
