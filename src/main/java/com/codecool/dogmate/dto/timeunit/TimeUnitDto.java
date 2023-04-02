package com.codecool.dogmate.dto.timeunit;

import java.time.LocalDateTime;

public record TimeUnitDto(

        Integer id,
        String name,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive
){
}
