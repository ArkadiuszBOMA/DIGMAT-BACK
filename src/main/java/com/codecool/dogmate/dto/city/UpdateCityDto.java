package com.codecool.dogmate.dto.city;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateCityDto(
        @NotNull
        Integer id,
        @Size(min = 5, max = 50, message = "Nazwa miasta musi mieś długość minimalną 5 i maksymalną 50 znaków")
        String name,
        @NotNull(message = "Nie wybrałeś powiatu")
        Integer province
){
}
