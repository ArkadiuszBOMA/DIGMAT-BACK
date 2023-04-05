package com.codecool.dogmate.dto.province;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateProvinceDto(
        @NotNull
        Integer id,
        @Size(min = 5, max = 50, message = "Nazwa powiatu musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String name,
        @NotNull
        String terytId,
        @NotNull
        Integer voivodeship
){
}
