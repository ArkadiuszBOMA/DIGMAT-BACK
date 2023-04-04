package com.codecool.dogmate.dto.animaltype;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UpdateAnimalTypeDto(

        @NotNull
        Integer id,
        @Size(min = 3, max = 50, message = "Nazwa musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String name,
        String description
){
}