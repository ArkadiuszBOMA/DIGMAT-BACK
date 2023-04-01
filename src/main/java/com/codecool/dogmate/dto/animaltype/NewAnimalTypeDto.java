package com.codecool.dogmate.dto.animaltype;

import javax.validation.constraints.Size;

public record NewAnimalTypeDto(
        @Size(min = 5, max = 50, message = "Nazwa musi mieć długość minimalną 5 i maksymalną 50 znaków")
        String name,
        String description
){
}