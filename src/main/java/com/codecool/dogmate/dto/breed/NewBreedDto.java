package com.codecool.dogmate.dto.breed;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record NewBreedDto(
        @Size(min = 3, max = 55, message = "Rasa zwierzaka musi mieć długość minimalną 3 i maksymalną 55 znaków")
        String name,
        @NotNull
        Integer animalType
) {
}
