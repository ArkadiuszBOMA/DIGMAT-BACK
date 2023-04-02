package com.codecool.dogmate.dto.usertype;


import java.time.LocalDateTime;

public record UserTypeDto (

        Integer id,
        String name,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive
){
}
