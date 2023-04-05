package com.codecool.dogmate.dto.voivodeship;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateVoivodeshipDto(

        @NotNull
        Integer id,
        @Size(min = 5, max = 20, message = "Nazwa województwa musi mieć długość minimalną 5 i maksymalną 20 znaków")
        String name,
        @Size(max = 2, message = "Kod TERYT województwo to 2 cyfry")
        String terytId
){
}