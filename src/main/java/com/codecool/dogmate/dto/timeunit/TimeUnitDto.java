package com.codecool.dogmate.dto.timeunit;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public record TimeUnitDto(

        Integer id,
        @Size(min = 5, max = 10, message = "Nazwa jednostki miary musi mieć długość minimalną 5 i maksymalną 10 znaków")
        String name,
        LocalDateTime date_create,
        LocalDateTime date_modify,
        LocalDateTime date_archive,
        Boolean archive
){
}
