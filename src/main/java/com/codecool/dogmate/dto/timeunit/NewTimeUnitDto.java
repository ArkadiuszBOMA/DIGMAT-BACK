package com.codecool.dogmate.dto.timeunit;

import javax.validation.constraints.Size;

public record NewTimeUnitDto(
        @Size(min = 5, max = 10, message = "Nazwa jednostki miary musi mieć długość minimalną 5 i maksymalną 10 znaków")
        String name
){
}
