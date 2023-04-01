package com.codecool.dogmate.dto.voivodeship;

import javax.validation.constraints.Size;

public record NewVoivodeshipDto(

        @Size(min = 5, max = 20, message = "Nazwa województwa musi mieć długość minimalną 5 i maksymalną 20 znaków")
        String name,
        @Size(max = 2, message = "Kod TERYT województwo to 2 cyfry")
        String terytId
) {
}
